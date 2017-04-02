package com.csueb.ds.database;

import java.util.ArrayList;
import java.util.List;

import com.csueb.ds.customs.Constants;
import com.csueb.ds.models.Conference;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/*Mongo DB Connection: To connect with server */
public class MongoDBConnection {

	/* Declaration */
	private String DBCollection = "conference";

	private static MongoDBConnection dbSingleton;
	//private static final int db_port = 59767;
//	private static final int db_port = 27017;
	//private static final String db_host = "localhost";
	private static final String userName = "admin";
	private static final String pass = "admin123";
	private static final String DBName = "conference-organizer";
	private String mongoDBURI = "";
	
	MongoClient mongoClient;
	DB mongodb;
	com.mongodb.DBCollection table;
	MongoClientURI mongoURI;

	private MongoDBConnection() throws Exception{
		MongoClientURI connectionString = new MongoClientURI
				("mongodb://maulikadmin:admin123@ds159767.mlab.com:59767/conference-organizer");
		MongoClient mongoClient = new MongoClient(connectionString);
		DB mongodb = mongoClient.getDB("conference-organizer");
		table = mongodb.getCollection(DBCollection);
	};

	public static MongoDBConnection getInstance() throws Exception {
		if (dbSingleton == null) {
			dbSingleton = new MongoDBConnection();
		}
		return dbSingleton;
	}

	public DB checkConnectionDB() throws Exception {

		if (mongoClient == null) {
			try {
				mongoDBURI = "mongodb://"+userName+":"+pass+"@ds159767.mlab.com:59767/conference-organizer";
				mongoClient = new MongoClient(mongoDBURI);
				// mongoClient = new MongoClient(db_host, db_port);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			/*
			 * MongoClientURI connectionString = new MongoClientURI
				("mongodb://maulikadmin:admin123@ds159767.mlab.com:59767/conference-organizer");
		MongoClient mongoClient = new MongoClient(connectionString);
		
		MongoDatabase db = mongoClient.getDatabase("ConferenceDB");
		
		System.out.println(db.getCollection("conference").count());
			 * */
			
		}
		if (mongodb == null) {
			mongodb = mongoClient.getDB(DBName);
			System.out.println(mongodb.getCollection(DBCollection));
		}

		// boolean auth = db.authenticate("admin","admin123".toCharArray());

		return mongodb;
	}

	/*METHOD: Insert into database*/
	public void insertToMongoDB(Conference conference) throws Exception {
		/*Conference data from user*/
		/*
		 * String conf_title, String conf_details, String conf_location,
			String conf_datetime, String guest_speaker
		 * */
		checkConnectionDB();
		if (table == null) {
			table = mongodb.getCollection(DBCollection);
		}
		BasicDBObject document = new BasicDBObject();
		document.put(Constants.CONF_TITLE, conference.getConf_title());
		document.put(Constants.CONF_DETAILS, conference.getConf_details());
		document.put(Constants.CONF_LOCATION, conference.getConf_location());
		document.put(Constants.CONF_DATETIME, conference.getConf_date());
		document.put(Constants.GUEST_SPEAKER, conference.getGuest_speaker());
		table.insert(document);
	}

	/*Update*/
	public void updateConference(Conference conference) throws Exception {
		checkConnectionDB();
		if (table == null) {
			table = mongodb.getCollection(DBCollection);
		}
		BasicDBObject query = new BasicDBObject();
		query.put("conf_title", conference.getConf_title());
		query.put("conf_details", conference.getConf_details());
		query.put("conf_location", conference.getConf_location());
		query.put("conf_datetime", conference.getConf_date());
		query.put("guest_speaker", conference.getGuest_speaker());
		
	}
	
	/*public void deleteConference(Conference conference){
		
		table = mongodb.getCollection(DBCollection);
		
		Document document = new Document();
		document.put("conf_id",""+conference.getConf_ID());
		
		table.remove(document);
	}*/

	/*Find*/
	public List<Conference> findAllConference(){
		DBCursor conferences =  table.find();
		List<Conference> confList = new ArrayList<Conference>();
		while(conferences.hasNext()){
			DBObject doc = conferences.next();
			Conference conf = new Conference();
			conf.setConf_title((String)doc.get("conf_title"));
			conf.setConf_date((String)doc.get("conf_date"));
			conf.setConf_time((String)doc.get("conf_time"));
			conf.setConf_location((String)doc.get("conf_location"));
			conf.setGuest_speaker((String)doc.get("guest_speaker"));
			conf.setConf_details((String)doc.get("conf_details"));
			confList.add(conf);
		}
		return confList;
	}
}
