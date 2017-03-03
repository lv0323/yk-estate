package com.lyun.estate.core.supports.types;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public static class CLIENT_ID {
        public static final int WEB = 1000;
        public static final int MGT = 1001;
        public static final int ANDROID = 2000;
        public static final int iOS = 3000;

        private final static List<Integer> clients = new ArrayList<>();

        static {
            clients.add(WEB);
            clients.add(MGT);
            clients.add(ANDROID);
            clients.add(iOS);
        }

        public static List<Integer> getClients() {
            return clients;
        }
    }


}
