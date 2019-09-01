package com.myIdeas.number7;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class ConnectDB {
	
	public DBCollection connectMongo() {
	
	try {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db=mongo.getDB("local");
		DBCollection table = db.getCollection("postmaster");
	    return table;
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    return null;
	
	}
	
}
}
