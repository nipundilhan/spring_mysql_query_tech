package com.sprng.tech.util;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class PaginationUtil {

    @PersistenceContext
    private EntityManager entityManager;
    private static final String REGEX = "(SELECT)(.*page_param)(.*)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public PageImpl getPageResult(Query dataQuery, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);

        BigInteger count;
        if (dataQuery.unwrap(org.hibernate.query.NativeQuery.class).getQueryString().matches("(?i).*GROUP BY.*")) {
            count = BigInteger.valueOf(dataQuery.getResultList().stream().count());
        } else {
            Query countQuery = convertToCountQuery(dataQuery);
            dataQuery.getParameters().forEach(param -> {
                String paramName = param.getName();
                countQuery.setParameter(paramName, dataQuery.getParameterValue(paramName));
            });
            count = (BigInteger) countQuery.getSingleResult();
        }

        dataQuery.setFirstResult((int) pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
        List result = dataQuery.getResultList();

//    	List<BigInteger> resultList = countQuery.getResultList();
//    	BigInteger count = resultList == null || resultList.isEmpty() ? null : resultList.get(0);

        return new PageImpl<>(result, pageable, count.longValue());
    }
    
    public PageImpl getPageResult(Query dataQuery,Query countQuery, int page, int size) throws Exception {
		Pageable pageable = PageRequest.of(page, size);
    	
    	
    	dataQuery.setFirstResult((int)pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
    	List result = dataQuery.getResultList();
    	
    	BigInteger count = (BigInteger) countQuery.getSingleResult();
    	
//    	List<BigInteger> resultList = countQuery.getResultList();
//    	BigInteger count = resultList == null || resultList.isEmpty() ? null : resultList.get(0);
    	
    	PageImpl pageImpl = new PageImpl<>(result, pageable, count.longValue());    	
    	return new PageImpl<>(result, pageable, count.longValue());
    	
    	
    }



    private Query convertToCountQuery(Query dataQuery) throws Exception {
        String dataSql = dataQuery.unwrap(org.hibernate.query.NativeQuery.class).getQueryString();
        System.out.println("dataQuery= " + dataSql);
        String countSql = null;
        StringBuffer sb = new StringBuffer(dataSql.length());
        Matcher m = PATTERN.matcher(dataSql);
        if (m.find()) {
            m.appendReplacement(sb, "$1 count(*) $3");
            countSql = sb.toString();
        } else {
            throw new Exception("Cannot convert to a count query.");
        }
        System.out.println("countSql= " + countSql);
        return this.entityManager.createNativeQuery(countSql);
    }
}