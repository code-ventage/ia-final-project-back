package org.suehay.ia_final_project_back.repository;

import org.jpl7.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultRepositoryImpl implements ConsultRepository {

    private @Value("${consult.file}") String file;

    @Override
    public Query getQuery(StringBuilder consultResult) {
        initialize();
        return new Query(consultResult.toString());
    }

    private void initialize() {
        Query q = new Query("consult(" + file + ").");
        q.open();
        q.getSolution();
        q.close();
    }

}