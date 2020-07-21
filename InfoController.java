package com.example.talent4gig;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class InfoController {
	
	@Autowired
	private InputXMLRepository inputRep;	
 
	    
	@PostMapping(
			consumes = MediaType.APPLICATION_XML_VALUE ,
			produces = MediaType.APPLICATION_XML_VALUE )
	public  ResponseEntity<DataOutput> customerInformation(@RequestBody(required = false) DataInput input) {
		
		DataOutput dOutput;
			String sError = validateInput(input);
			
			dOutput = new DataOutput();
			if (!sError.isEmpty()) {
				dOutput.setId((long)0);
				dOutput.setStatus("ERR"+sError);
			} else {
				
				InputXML ixm = new InputXML();
				ixm.setSource(input.getSource());
	
				int iValue = (int)(input.getValue() * 100) ; 
				ixm.setValue(iValue);
				
				ArrayList<String> aTag = input.getTags();
				switch (aTag.size()) {
				case 1:
					ixm.setTag1(aTag.get(0));
					break;
					
				case 2:
					ixm.setTag1(aTag.get(0));
					ixm.setTag2(aTag.get(1));
					break;
					
				case 3:
					ixm.setTag1(aTag.get(0));
					ixm.setTag2(aTag.get(1));
					ixm.setTag3(aTag.get(2));
					break;
				}
	
				
				InputXML ixmnew = inputRep.save(ixm);
				dOutput.setId(ixmnew.getId());
				inputRep.flush();
				dOutput.setStatus("OK");
		};

		return  new ResponseEntity<DataOutput>(dOutput, HttpStatus.OK);
    	
    }

	
	@Test
	public void testPostXML() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:8080";
	    URI uri = new URI(baseUrl);
	 
	    DataInput in = new DataInput();
	    in.setSource("APPA");
	    in.setValue((long)123.45);
	    ArrayList<String> atag = new ArrayList<String>();
	    atag.add("APP");
	    atag.add("FRONTEND");
	    in.setTag(atag);
	    
	    HttpHeaders headers = new HttpHeaders();
	    HttpEntity<DataInput> request = new HttpEntity<>(in, headers);
	     
	    ResponseEntity<DataOutput> result = restTemplate.postForEntity(uri, request, DataOutput.class);

	    
	    System.out.println(result);
	}
	
	
	@GetMapping(path="/getbyid",
			consumes = MediaType.APPLICATION_XML_VALUE ,
			produces = MediaType.APPLICATION_XML_VALUE )
	public ResponseEntity<DataOutputTotSource> getValueXML(@RequestBody DataId dataId) 	{
		DataOutputTotSource outputTot = new DataOutputTotSource();
		
		Long id = dataId.getId();
		InputXML input = inputRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid id " + dataId.getId()));
		String sSource = input.getSource();
		
		List<InputXML> lInput= inputRep.findAll();
		
		outputTot.setId(id);
		outputTot.setSource(sSource);
		double lTot = (double)0; 
		
		double dValue;
		for (InputXML ix : lInput) {
			dValue = (double)(ix.getValue()) / 100;
			if (ix.getSource() == sSource) {
				
				lTot += dValue;
				if ( ix.getTag1() != null) {
					outputTot.setTag(ix.getTag1(), dValue);
				}
				
				if ( ix.getTag2() != null) {
					outputTot.setTag(ix.getTag2(), dValue);
				}
				
				if ( ix.getTag3() != null) {
					outputTot.setTag(ix.getTag3(), dValue);
				}
				
			}
		}
		outputTot.setTotValue(lTot);
		return  new ResponseEntity<DataOutputTotSource>(outputTot, HttpStatus.OK);
	}
	
	
	@GetMapping(path="/errors",
			produces = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ErrorsInput>> getListErrors(){
		List<ErrorsInput> lErrors = new ArrayList<ErrorsInput>();
		
		lErrors.add(new ErrorsInput("01", 
			"Data Source can be only in (APPA, APPB,APPC,APPD). "+
			"Value must be between 0 and 1000 "+
			"There can be max 3 TAGS "));

		lErrors.add(new ErrorsInput("02",
				"Data Source can be only in (APPA, APPB,APPC,APPD). "+
				"Value must be between 0 and 1000 "));
		
		lErrors.add(new ErrorsInput("03",
				"Data Source can be only in (APPA, APPB,APPC,APPD). "+
				"There can be max 3 TAGS "));
		
		lErrors.add(new ErrorsInput("04",
			"Data Source can be only in (APPA, APPB,APPC,APPD) ."));
		
		lErrors.add(new ErrorsInput("05",
				"Value must be between 0 and 1000"));
		
		lErrors.add(new ErrorsInput("06",
			"There can be max 3 TAGS" ));
		
		lErrors.add(new ErrorsInput("07",
				"Value must be between 0 and 1000. "+
						"There can be max 3 TAGS "));
		
		return new ResponseEntity<List<ErrorsInput>>(lErrors, HttpStatus.OK);
	}
	
	@Test
	public void testGetErrorList() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:8080/errors";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
	    System.out.println(result);
	     /*
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().contains("employeeList"));
	    */
	}
	
	
	private String validateInput(DataInput input) {
		String sError = "";
		Boolean lErrSource = false;
		Boolean lErrValue = false;
		Boolean lErrTags = false;
		
		if ( (!input.getSource().equals((String)"APPA")) &&
				(!input.getSource().equals((String)"APPB")) &&
				(!input.getSource().equals((String)"APPC")) &&
				(!input.getSource().equals((String)"APPD")) ) {
			lErrSource = true;
			
		} 
		
		if (input.getValue() < 0 ||
				input.getValue() > 1000) {
			//sError += "Value must be between 0 and 1000";
			lErrValue = true;
		}
		
		if (input.getTags().size() > 3) {
			lErrTags = true;
			//sError += "There can be max 3 TAGS" ;
		}

		if (lErrSource && lErrValue && lErrTags ) { 
			sError = "01";
			//Data Source can be only in (APPA, APPB,APPC,APPD)
			//Value must be between 0 and 1000
			//There can be max 3 TAGS
		} else if (lErrSource && lErrValue && !lErrTags ) { 
			sError = "02";
			//Data Source can be only in (APPA, APPB,APPC,APPD)
			//Value must be between 0 and 1000
		} else if (lErrSource && !lErrValue && lErrTags ) { 
			sError = "03";
			//Data Source can be only in (APPA, APPB,APPC,APPD)
			//There can be max 3 TAGS
		} else if (lErrSource && !lErrValue && !lErrTags ) { 
			sError = "04";
			//Data Source can be only in (APPA, APPB,APPC,APPD)
		} else if (!lErrSource && lErrValue && !lErrTags ) { 
			sError = "05";
			//Value must be between 0 and 1000
		} else if (!lErrSource && !lErrValue && lErrTags ) {
			sError = "06";
			//There can be max 3 TAGS
		} else if (!lErrSource && lErrValue && lErrTags ) {
			sError = "07";
			//Value must be between 0 and 1000
			//There can be max 3 TAGS
			
		};
		
			
		return sError;
	}

}
