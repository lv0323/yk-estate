package com.lyun.estate.biz.report.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jesse on 2017/1/25.
 */
@Service
public class ReportDataSourceUtils {
    private final Map<String, ReportInfo> dataSourceMap = new ConcurrentHashMap<String, ReportInfo>();
    private final static String CONNECTOR = ":";
    private final static String DEFAULT_REGION = "DEFAULT";

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() throws IOException, SAXException, ParserConfigurationException {
//        parseDataSourceXml(context.getEnvironment().getRequiredProperty("report.datasource"));
        //TODO context获取报错
        parseDataSourceXml("/Users/jesse/git/yk-estate/estate/estate-biz/src/main/resources/estate/biz/ReportInfo.xml");
    }

    private void parseDataSourceXml(String xmlPath) throws ParserConfigurationException, IOException, SAXException {
        Assert.hasLength(xmlPath, "需指定解析的文件路径");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(xmlPath);
        NodeList nodeList = document.getElementsByTagName("ReportInfo");
        ReportInfo reportInfo = null;
        for (int i =0; i < nodeList.getLength(); i++) {
            reportInfo = new ReportInfo();
            Node node = nodeList.item(i);
            Element element = (Element) node;

            String id = element.getAttribute("id");
            Assert.hasLength(id, "ID不能为空");
            reportInfo.setId(id);

            String region = element.getAttribute("region");
            Assert.hasLength(region, "ID不能为空");
            reportInfo.setRegion(region);

            String key = region + CONNECTOR + id;
            if (dataSourceMap.containsKey(key)) {
                Assert.hasLength(id, key + "重复存在");
            }

            for (Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    String name = n.getNodeName();
                    if ("SQL".equalsIgnoreCase(name)) {
                        String sql = n.getTextContent();
                        Assert.hasLength(sql, "SQL不能为空");
                        reportInfo.setSql(sql);
                    } else if ("Header".equalsIgnoreCase(name)) {
                        String r = n.getTextContent();
                        List<String> headerList = new ArrayList<String>();
                        for (Node s = n.getFirstChild(); s!= null; s = s.getNextSibling()) {
                            if (n.getNodeType() == Node.ELEMENT_NODE) {
                                String valueName = s.getNodeName();
                                if ("Column".equalsIgnoreCase(valueName)) {
                                    String value = s.getTextContent();
                                    headerList.add(value);
                                }
                            }
                        }
                        reportInfo.setReportHeader(headerList);
                    }
                }
            }
            Assert.hasLength(reportInfo.getSql(), "SQL不能为空");
            dataSourceMap.put(key, reportInfo);
        }
    }

    ReportInfo getReportInfo(String id, String region) {
        Assert.hasLength(id, "未指定要查询的id");
        String key = null;
        if (StringUtils.isEmpty(region)) {
            key = DEFAULT_REGION + CONNECTOR + id;
        } else {
            key =region + CONNECTOR + id;
        }
        return dataSourceMap.get(key);
    }

    boolean updateReportInfo(String id, String region, ReportInfo reportInfo) {
        Assert.hasLength(id, "未指定要查询的id");
        String key = null;
        if (StringUtils.isEmpty(region)) {
            key = DEFAULT_REGION + CONNECTOR + id;
        } else {
            key =region + CONNECTOR + id;
        }
        dataSourceMap.put(key, reportInfo);
        return true;
    }
}
