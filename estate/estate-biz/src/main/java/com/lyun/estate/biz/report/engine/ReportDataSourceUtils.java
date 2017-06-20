package com.lyun.estate.biz.report.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReportDataSourceUtils {

    private final Map<String, ReportInfo> dataSourceMap = new ConcurrentHashMap<String, ReportInfo>();
    private final static String CONNECTOR = ":";
    private final static String DEFAULT_REGION = "DEFAULT";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException {
        try (InputStream inputStream = context.getResource(env.getProperty("report.datasource")).getInputStream()) {
            parseDataSourceXml(inputStream);
        }
    }

    private void parseDataSourceXml(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        Assert.notNull(inputStream, "InputStream is null");
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        NodeList nodeList = document.getElementsByTagName("ReportInfo");
        ReportInfo reportInfo = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            reportInfo = new ReportInfo();
            Node node = nodeList.item(i);
            Element element = (Element) node;

            String id = element.getAttribute("id");
            Assert.hasLength(id, "ID is null");
            reportInfo.setId(id);

            String region = element.getAttribute("region");
            Assert.hasLength(region, "ID is null");
            reportInfo.setRegion(region);

            String key = region + CONNECTOR + id;
            if (dataSourceMap.containsKey(key)) {
                Assert.hasLength(id, key + "already exist");
            }

            for (Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    String name = n.getNodeName();
                    if ("SQL".equalsIgnoreCase(name)) {
                        String sql = n.getTextContent();
                        Assert.hasLength(sql, "SQL is null");
                        reportInfo.setSql(sql);
                    } else if ("Header".equalsIgnoreCase(name)) {
                        String r = n.getTextContent();
                        List<ReportHeader> reportHeaderList = new ArrayList<>();
                        ReportHeader reportHeader = null;
                        for (Node s = n.getFirstChild(); s != null; s = s.getNextSibling()) {
                            if (n.getNodeType() == Node.ELEMENT_NODE) {
                                reportHeader = new ReportHeader();
                                String valueName = s.getNodeName();
                                if ("Column".equalsIgnoreCase(valueName)) {
                                    boolean hasType = false;
                                    for (Node sc = s.getFirstChild(); sc != null; sc = sc.getNextSibling()) {
                                        if (sc.getNodeType() == Node.ELEMENT_NODE) {
                                            hasType = true;
                                            String scValueName = sc.getNodeName();
                                            if ("name".equalsIgnoreCase(scValueName)) {
                                                String scValue = sc.getTextContent();
                                                reportHeader.setName(scValue);
                                            } else if ("type".equalsIgnoreCase(scValueName)) {
                                                String scValue = sc.getTextContent();
                                                reportHeader.setType(Class.forName(scValue));
                                            }
                                        }
                                    }
                                    if (!hasType) {
                                        String scValue = s.getTextContent();
                                        reportHeader.setName(scValue);
                                        reportHeader.setType(String.class);
                                    }
                                    reportHeaderList.add(reportHeader);
                                }
                            }
                        }
                        reportInfo.setHeaderList(reportHeaderList);
                    }
                }
            }
            Assert.hasLength(reportInfo.getSql(), "SQL is null");
            dataSourceMap.put(key, reportInfo);
        }
    }

    ReportInfo getReportInfo(String id, String region) {
        Assert.hasLength(id, "id is null");
        String key = null;
        if (StringUtils.isEmpty(region)) {
            key = DEFAULT_REGION + CONNECTOR + id;
        } else {
            key = region + CONNECTOR + id;
        }
        return dataSourceMap.get(key);
    }

    boolean updateReportInfo(String id, String region, ReportInfo reportInfo) {
        Assert.hasLength(id, "id is null");
        String key = null;
        if (StringUtils.isEmpty(region)) {
            key = DEFAULT_REGION + CONNECTOR + id;
        } else {
            key = region + CONNECTOR + id;
        }
        dataSourceMap.put(key, reportInfo);
        return true;
    }
}
