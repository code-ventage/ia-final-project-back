package org.suehay.ia_final_project_back.repository;

import org.jpl7.Query;

public interface ConsultRepository {
    Query getQuery(StringBuilder nums);
}