package com.AWS.Figma.Dashboard.dao;

import com.AWS.Figma.Dashboard.entity.Sales;
import com.AWS.Figma.Dashboard.dto.DailySalesDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public class SalesDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Sales save(Sales sales) {
        if (sales.getId() == null) {
            entityManager.persist(sales);
            return sales;
        } else {
            return entityManager.merge(sales);
        }
    }

    public Sales findById(Long id) {
        return entityManager.find(Sales.class, id);
    }

    public List<Sales> findAll() {
        TypedQuery<Sales> query = entityManager.createQuery(
                "SELECT s FROM Sales s ORDER BY s.saleDate DESC", Sales.class);
        return query.getResultList();
    }

    public List<DailySalesDto> getDailySalesForMonth(int year, int month) {
        TypedQuery<DailySalesDto> query = entityManager.createQuery(
                "SELECT new com.AWS.Figma.Dashboard.dto.DailySalesDto(" +
                        "s.saleDate, " +
                        "SUM(s.itemsSold), " +
                        "SUM(s.totalAmount)) " +
                        "FROM Sales s " +
                        "WHERE YEAR(s.saleDate) = :year AND MONTH(s.saleDate) = :month " +
                        "GROUP BY s.saleDate " +
                        "ORDER BY s.saleDate", DailySalesDto.class);

        query.setParameter("year", year);
        query.setParameter("month", month);
        return query.getResultList();
    }

    public Integer getTotalItemsSoldForMonth(int year, int month) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(s.itemsSold), 0) FROM Sales s " +
                        "WHERE YEAR(s.saleDate) = :year AND MONTH(s.saleDate) = :month", Long.class);

        query.setParameter("year", year);
        query.setParameter("month", month);
        return query.getSingleResult().intValue();
    }

    public Double getTotalRevenueForMonth(int year, int month) {
        TypedQuery<Double> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(s.totalAmount), 0.0) FROM Sales s " +
                        "WHERE YEAR(s.saleDate) = :year AND MONTH(s.saleDate) = :month", Double.class);

        query.setParameter("year", year);
        query.setParameter("month", month);
        return query.getSingleResult();
    }

    public Long getTotalTransactionsForMonth(int year, int month) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM Sales s " +
                        "WHERE YEAR(s.saleDate) = :year AND MONTH(s.saleDate) = :month", Long.class);

        query.setParameter("year", year);
        query.setParameter("month", month);
        return query.getSingleResult();
    }

    public List<Sales> findByDateRange(LocalDate startDate, LocalDate endDate) {
        TypedQuery<Sales> query = entityManager.createQuery(
                "SELECT s FROM Sales s " +
                        "WHERE s.saleDate BETWEEN :startDate AND :endDate " +
                        "ORDER BY s.saleDate DESC", Sales.class);

        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    public List<Sales> findByItemId(Long itemId) {
        TypedQuery<Sales> query = entityManager.createQuery(
                "SELECT s FROM Sales s " +
                        "WHERE s.item.id = :itemId " +
                        "ORDER BY s.saleDate DESC", Sales.class);

        query.setParameter("itemId", itemId);
        return query.getResultList();
    }
}