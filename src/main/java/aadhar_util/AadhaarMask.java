/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aadhar_util;

/**
 *
 * @author acer
 */
public class AadhaarMask {
  
   
    
    
    
       
    public static String maskAadhaarString(String strText, int start, int end, char maskChar) 
        throws Exception{
        
        if(strText == null || strText.equals(""))
            return "";
        
        if(start < 0)
            start = 0;
        
        if( end > strText.length() )
            end = strText.length();
            
        if(start > end)
            throw new Exception("End index cannot be greater than start index");
        
        int maskLength = end - start;
        
        if(maskLength == 0)
            return strText;
        
        StringBuilder sbMaskString = new StringBuilder(maskLength);
        
        for(int i = 0; i < maskLength; i++){
            sbMaskString.append(maskChar);
        }
        
        return strText.substring(0, start) 
            + sbMaskString.toString() 
            + strText.substring(start + maskLength);
    }
    
     
    
    
    
    
    /**
   * @param number The number in plain format
   * @param mask The  mask pattern. 
   *    Use # to include the digit from the position. 
   *    Use x to mask the digit at that position.
   *    Any other char will be inserted.
   *
   * @return The masked card number
   */
   public static String maskAadhaarNumber(String number, String mask) {
 
      int index = 0;
      StringBuilder masked = new StringBuilder();
      for (int i = 0; i < mask.length(); i++) {
         char c = mask.charAt(i);
         if (c == '#') {
            masked.append(number.charAt(index));
            index++;
         } else if (c == 'x') {
            masked.append(c);
            index++;
         } else {
            masked.append(c);
         }
      }
      return masked.toString();
   }
    
    
    
    
    
}
