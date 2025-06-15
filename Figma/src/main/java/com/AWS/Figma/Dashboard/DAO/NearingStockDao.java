package com.AWS.Figma.Dashboard.DAO;

import com.AWS.Figma.Dashboard.Query.NearingStockQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NearingStockDao {

    @Autowired
    private NearingStockQuery query;

    public long getNearingStockCount() {
        return query.fetchNearingStockCount();
    }
}
