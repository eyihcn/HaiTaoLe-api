package com.tomtop.api.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;

public class JaxbUtil {

	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, JAXBContext jaxbContext) {
		T t = null;
		try {

			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			t = (T) unMarshaller.unmarshal(new StringReader(xml));
			System.out.println(t);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 
	 * java对象转换为xml
	 */
	public static String beanToXml(JAXBContext jaxbContext, Object obj, String encoding) {
		StringWriter writer = new StringWriter();
		Marshaller marshaller;
		try {
			marshaller = createMarshaller(jaxbContext, encoding);
			marshaller.marshal(obj, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	 /** 
     * Java Object->Xml, 特别支持对Root Element是Collection的情形. 
     */  
    public static String beansToXml(JAXBContext jaxbContext, Collection<?> root, String rootName, String encoding) {  
        try {  
            CollectionWrapper wrapper = new CollectionWrapper();  
            wrapper.collection = root;  
  
            JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(  
                    new QName(rootName), CollectionWrapper.class, wrapper);  
  
            StringWriter writer = new StringWriter();  
            createMarshaller(jaxbContext, encoding).marshal(wrapperElement, writer);  
  
            return writer.toString();  
        } catch (JAXBException e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
	/**
	 * xml转换为java对象 Marshaller类实现将对象转换为xml,
	 * 创建Marshaller, 设定encoding(可为Null).
	 */
	public static Marshaller createMarshaller(JAXBContext jaxbContext, String encoding) {
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}
			return marshaller;
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	/** 
     * 封装Root Element 是 Collection的情况. 
     */  
    public static class CollectionWrapper {  
        @SuppressWarnings("rawtypes")
		@XmlAnyElement  
        protected Collection collection;  
    }  
	
}
