package fr.cyberbase.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.jboss.resteasy.util.Hex;

import fr.cyberbase.entities.ProfessionnelEntity;

public class CookieTools {
	//format du cookie: 1441048867730------CYBERPRO------P0000003------nom------prenom------4
	private static final String KEY 				= "ad77bedbe8bc93ccc779e655bed7dcff";
    private static final String SEPARATOR 			= "------";
    private static final String CHECK_MSG_PRO_USER 	= "CYBERPRO";
    private static final String CHECK_MSG_ADMIN 	= "CYBERADMIN";
    public static final String COOKIE_KEY 			= "CBASELOGIN";

    private SecretKey key;
    
    /**
     * @brief fonction de cryptage d'une chaine
     * @param String chain
     * @return String chaine criptÃ©e
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
	public String encrypt(String chain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
	IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipherOut = Cipher.getInstance("AES");
		cipherOut.init(Cipher.ENCRYPT_MODE, getKey());
		String encryptedString = Hex.encodeHex(cipherOut.doFinal(chain.getBytes()));
		return encryptedString;
	}
	
	
	public String decrypt(String chain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
	UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipherIn = Cipher.getInstance("AES");
		cipherIn.init(Cipher.DECRYPT_MODE, getKey());
		return  new String(cipherIn.doFinal(Hex.decodeHex(chain.toString())), "UTF-8");	
	}
	  
	public Login getLogin(String encryptToken) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException,
	IllegalBlockSizeException, BadPaddingException,InvalidKeyException 
	{
		String token = decrypt(encryptToken);
		String[] tokenParts = token.split(SEPARATOR);
		
		if(!tokenParts[1].equals(CHECK_MSG_PRO_USER) && !tokenParts[1].equals(CHECK_MSG_ADMIN))
			return null;
		
		Login login = new Login();
		login.setLoginTechId(tokenParts[2]);
		Calendar maxAge = Calendar.getInstance();
		
		maxAge.setTimeInMillis(Long.valueOf(tokenParts[0]));
		
		login.setMaxAge(maxAge);
		login.setNom(tokenParts[3]);
		login.setPrenom(tokenParts[4]);
		login.setSiteId(Integer.valueOf(tokenParts[5]));
		if(tokenParts[1].equals(CHECK_MSG_ADMIN))
			login.setAdmin(true);
        
		return login;
    
    }

	
	/**
	 * @brief fonction de génération d'une valeur de cookie pour un utilisateur classique
	 * @param ProfessionnelEntity 
	 * @return String valeur criptée d'un cookie
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
    public String generateNewProfessionalToken(ProfessionnelEntity pro) throws InvalidKeyException, NoSuchAlgorithmException,
    NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
    	//objet Calendar qui manipule les élements YEAR, MONTH etc et permet des conversions. Initialisation à l'heure locale
    	Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 4);
        Long expireTimeInMillis = calendar.getTimeInMillis();
        StringBuilder token = new StringBuilder();
        token.append(expireTimeInMillis);
        token.append(SEPARATOR);
        token.append(CHECK_MSG_ADMIN);
        token.append(SEPARATOR);
        token.append(pro.getTech_id());
        token.append(SEPARATOR);
        token.append(pro.getNom_professionnel());
        token.append(SEPARATOR);
        token.append(pro.getPrenom_professionnel());
        token.append(SEPARATOR);
        token.append(pro.getSite_reference().getId_site());
        
        return encrypt(token.toString());
    	
    }
    
    /**
	 * @brief fonction de génération d'une valeur de cookie pour un utilisateur administrateur
	 * @param ProfessionnelEntity 
	 * @return String valeur criptée d'un cookie
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
    public String generateNewAdminToken(ProfessionnelEntity pro) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
    IllegalBlockSizeException, BadPaddingException
    {
    	
    	Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 7);
        Long expireTimeInMillis = calendar.getTimeInMillis();
        
        StringBuilder token = new StringBuilder();
        token.append(expireTimeInMillis);
        token.append(SEPARATOR);
        token.append(CHECK_MSG_PRO_USER);
        token.append(SEPARATOR);
        token.append(pro.getTech_id());
        token.append(SEPARATOR);
        token.append(pro.getNom_professionnel());
        token.append(SEPARATOR);
        token.append(pro.getPrenom_professionnel());
        token.append(SEPARATOR);
        token.append(pro.getSite_reference().getId_site());
        return encrypt(token.toString());
    	
    }
    
    
    /**
     * @brief recupération ou création de la clé sur laquelle se base le cryptage
     * @return SecretKey
     */
    public SecretKey getKey(){
    	if(key == null) {
            key =  new SecretKeySpec(Hex.decodeHex(KEY.toString()), "AES");
        }
        return key;	    	
    }
    
    
}
