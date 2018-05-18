package rsvier.workshop.dao;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;


public class ProductDAOImpMongo implements ProductDAO {
	
	private Logger logger = LogConnection.getLogger();
	private DB db;
	private DBCollection collection;
	
	public ProductDAOImpMongo() {
	
			try {
				db = DatabaseConnectionXML.getConnectionMongoDB();
				collection = db.getCollection("product");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public List<Product> getAllProducts() {
		
		List<Product> productList = new ArrayList<>();
		
		try(DBCursor cursor = collection.find().skip(1);){
			
			while(cursor.hasNext()) {
				
				DBObject object = cursor.next();
				BasicDBObject productObj = (BasicDBObject) object;
				
				Product.ProductBuilder productBuilder = new Product.ProductBuilder();
				productBuilder.productId(productObj.getInt("_id"));
				productBuilder.name(productObj.getString("name"));
				// Covert double to be BigDecimal
				BigDecimal price = BigDecimal.valueOf(productObj.getDouble("price"));
				price.setScale(2);
				productBuilder.price(price);
				productBuilder.stock(productObj.getInt("stock"));
				Product product = productBuilder.build();
				productList.add(product);
				
			}
			
		}
		return productList;
	}

	@Override
	public Product getProductById(int productId) {
		
		Product product = null;
		
		DBObject query = new BasicDBObject("_id",productId);
		
		try(DBCursor cursor = collection.find(query);){
			
			if(cursor.hasNext()) {
				
				DBObject object = cursor.next();
				BasicDBObject productObj = (BasicDBObject) object;
				
				Product.ProductBuilder productBuilder = new Product.ProductBuilder();
				productBuilder.productId(productObj.getInt("_id"));
				productBuilder.name(productObj.getString("name"));
				BigDecimal price = BigDecimal.valueOf(productObj.getDouble("price"));
				price.setScale(2);
				productBuilder.price(price);
				productBuilder.stock(productObj.getInt("stock"));
				product = productBuilder.build();
			
				
			}
			
		}
		return product;
	}

	@Override
	public Product getProductByName(String name) {
		
		Product product = null;
		
		DBObject query = new BasicDBObject("name",name);
		
		try(DBCursor cursor = collection.find(query);){
			
			if(cursor.hasNext()) {
				
				DBObject object = cursor.next();
				BasicDBObject productObj = (BasicDBObject) object;
				
				Product.ProductBuilder productBuilder = new Product.ProductBuilder();
				productBuilder.productId(productObj.getInt("_id"));
				productBuilder.name(productObj.getString("name"));
				BigDecimal price = BigDecimal.valueOf(productObj.getDouble("price"));
				price.setScale(2);
				productBuilder.price(price);
				productBuilder.stock(productObj.getInt("stock"));
				 product = productBuilder.build();
			
				
			}
			
		}
		return product;
	}

	@Override
	public void createProduct(Product product) {
		
		double generatedIdDouble = (Double) getNextSequence(" product_id");
		int generatedIdInt = (int)generatedIdDouble;
		DBObject newProduct = new BasicDBObject("_id",generatedIdInt)
				.append("name", product.getName())
				.append("price", product.getPrice().doubleValue())
				.append("stock", product.getStock());
		
		collection.insert(newProduct);
		
	}

	@Override
	public void updateProduct(Product product) {
		DBObject updateProduct = new BasicDBObject("_id",product.getProductId())
				.append("name", product.getName())
				.append("price", product.getPrice().doubleValue())
				.append("stock", product.getStock());
		
		collection.update(new BasicDBObject("_id",product.getProductId()), updateProduct);
	}

	@Override
	public void deleteProduct(Product product) {
		collection.remove(new BasicDBObject("_id", product.getProductId()));
		
	}
	
	// Method of auto-increment Id
		public Object getNextSequence(String productId) {

			BasicDBObject find = new BasicDBObject();
			find.put("_id", productId);
			BasicDBObject update = new BasicDBObject();
			update.put("$inc", new BasicDBObject("seq", 1));
			DBObject obj = collection.findAndModify(find, update);
	// return Object
			return obj.get("seq");  
		}


}
