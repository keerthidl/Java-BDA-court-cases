package com.finch.legal.opinion.app.services;

import java.io.DataInputStream;
import java.io.FileInputStream;

import org.apache.tomcat.jni.File;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * document uploader
 * @author dvsnk
 *
 */
public class AWSDocuploader {
	
	
	/**
	 * default constructor
	 */
	public AWSDocuploader() {
		
	}
	 
	/**
	 * upload file
	 */
	public void uploadfile() {
		 
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
	      try {
	    	  s3.putObject("", "", new DataInputStream(new FileInputStream("")), null);
	      } catch (AmazonServiceException e) {
	            System.err.println(e.getErrorMessage());
	            System.exit(1);
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
	}

}
