package services;

import mongoDB.MongoConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;


public class MainService {
	
	ApplicationContext ctx;
	MongoOperations mongoOperation;
	
	public MainService(){
		ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
		mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	}

}
