package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.model.Book;
import com.luckypants.model.Image;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllImagesCommand {
//String authorString = null;
	
	public ArrayList<Image> execute() {
	
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection imagecollection = conn.getCollection("images");
	
		DBCursor cursor = imagecollection.find();
		ArrayList<Image> Images = new ArrayList<Image>();
		GetImageCommand getimages = new GetImageCommand();
		try {
			while (cursor.hasNext()) {
				Image b = getimages.execute("_id",
						cursor.next().get("_id").toString());
				Images.add(b);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		cursor.close();
	}
	return Images;
	}
	
	public static void main(String[] args) {
		ListAllImagesCommand listBooks = new ListAllImagesCommand();
		ArrayList<Image> list = listBooks.execute();
		for(Image i: list){
			System.out.println(i.getImagename());
		}

	}

}
