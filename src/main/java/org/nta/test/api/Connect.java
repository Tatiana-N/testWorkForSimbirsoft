package org.nta.test.api;

import java.util.logging.Logger;

public interface Connect {
   void getConnect(Logger logger);
   <T> T getFrom();
}
