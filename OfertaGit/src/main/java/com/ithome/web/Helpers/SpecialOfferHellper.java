package com.ithome.web.Helpers;

import java.sql.SQLException;
import java.util.List;

public interface SpecialOfferHellper<T> {
     List<T> getStarted() throws SQLException;
     List<T> getAppearnace() throws SQLException;

}
