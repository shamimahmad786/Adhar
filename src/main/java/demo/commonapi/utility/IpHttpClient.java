/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.commonapi.utility;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;

public class IpHttpClient 
{
	private Boolean useProxy = false;

	
	public HttpURLConnection getHttpUrlConnection(String Url)
	{
		HttpURLConnection connection = null;
		try 
		{
			
			URL url = new URL(Url);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFollowRedirects(true);
			
		} catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} catch (ProtocolException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	
	
	public void sendRequest(String reqData,
			HttpURLConnection httpUrlConnection) 
	{
             System.out.println("httpUrlConnection :"+httpUrlConnection);
		DataOutputStream output = null;
		try 
		{
			output = new DataOutputStream(httpUrlConnection
					.getOutputStream());
                        System.out.println("output :"+output);
			output.writeBytes(reqData);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(output != null)
			{
				try 
				{
					output.close();
					
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	public int getResponseCode(HttpURLConnection httpUrlConnection) 
	{
		int responseCode = 401;
		try 
		{
			responseCode = httpUrlConnection.getResponseCode();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return responseCode;
	}

	public byte[] getResponseData(HttpURLConnection httpUrlConnection) 
	{
		DataInputStream is = null;
		// get ready to read the response from the cgi script
		try 
		{
			is = new DataInputStream(httpUrlConnection.getInputStream());
			int length = is.available();
                        //length=3000;
                        System.out.println("length:"+length);
			byte[] buff = new byte[length];
			is.readFully(buff);
			
			String data = new String(buff, "utf-8");
			return buff;
		} catch (IOException e) 
		{
		System.out.println("Error in getting Pub key");
                    e.printStackTrace();
		}
		finally
		{
			if(is != null)
			{
				try 
				{
					is.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
        
        
        
        public String getResponseDataString(HttpURLConnection httpUrlConnection) 
	{
		DataInputStream is = null;
		// get ready to read the response from the cgi script
		try 
		{
			is = new DataInputStream(httpUrlConnection.getInputStream());
			int length = is.available();
                          System.out.println("length:"+length);
			byte[] buff = new byte[length];
			is.readFully(buff);
			String data = new String(buff, "utf-8");
			return data;
		} catch (IOException e) 
		{
		System.out.println("Error in getting Pub key");
                    e.printStackTrace();
		}
		finally
		{
			if(is != null)
			{
				try 
				{
					is.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
        
        
                public String getResponseDataAsString(HttpURLConnection httpUrlConnection) 
                {
                    DataInputStream is = null;
		// get ready to read the response from the cgi script
		try 
		{
			is = new DataInputStream(httpUrlConnection.getInputStream());
			int read = is.read();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			while(read != -1)
			{
				stream.write(read);
				read = is.read();
			}
			
			byte[] buff = stream.toByteArray();
			stream.flush();
			stream.close();
			
			
//			int length = is.available();
//                          System.out.println("length:"+length);
//			byte[] buff = new byte[length];
//			is.readFully(buff);
			String data = new String(buff, "utf-8");
			return data;
		} catch (IOException e) 
		{
		System.out.println("Error in getting Pub key");
                    e.printStackTrace();
		}
		finally
		{
			if(is != null)
			{
				try 
				{
					is.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
                
       public byte[] getResponseDataAsByteArray(HttpURLConnection httpUrlConnection) 
                {
                    DataInputStream is = null;
		// get ready to read the response from the cgi script
		try 
		{
			is = new DataInputStream(httpUrlConnection.getInputStream());
			int read = is.read();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			while(read != -1)
			{
				stream.write(read);
				read = is.read();
			}
			
			byte[] buff = stream.toByteArray();
			stream.flush();
			stream.close();
                        return buff;
		} catch (IOException e) 
		{
		System.out.println("Error in getting Pub key");
                    e.printStackTrace();
		}
		finally
		{
			if(is != null)
			{
				try 
				{
					is.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}         
}
