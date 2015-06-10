package mongoDB;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

@Configuration
public class MongoConfig {

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
//		UserCredentials userCredentials = new UserCredentials("admin", "Oj5h44yzfW");
//		MongoCredential credential = MongoCredential.createMongoCRCredential("admin", "instrumentdb-test", "Oj5h44yzfW".toCharArray());		


		return new SimpleMongoDbFactory(new MongoClient("mongodb91688-mongodb.j.layershift.co.uk"), "instrumentdb-test");
		
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;

	}

}