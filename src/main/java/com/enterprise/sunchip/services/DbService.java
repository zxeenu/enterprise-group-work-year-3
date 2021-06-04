package main.java.com.enterprise.sunchip.services;

import Backend.BackendContext;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    // Bobs DB
//  public Backend.BackendContext BEContext = new BackendContext("jdbc:sqlserver://SILVER-NOTE:1433;databaseName=EnterpiseAppDb;user=zeenu;password=2OTF5FkZudUGv");
    // Sams DB
    public BackendContext BEContext = new BackendContext("jdbc:sqlserver://SILVER-NOTE:1433;databaseName=EnterpiseAppDb;user=zeenu;password=2OTF5FkZudUGv");
}
