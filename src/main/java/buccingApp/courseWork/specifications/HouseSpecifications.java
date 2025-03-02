package buccingApp.courseWork.specifications;

import buccingApp.courseWork.models.HouseForRent;
import org.springframework.data.jpa.domain.Specification;

public class HouseSpecifications {

    public static Specification<HouseForRent> hasCity(String city) {
        return (root, query, criteriaBuilder) ->
                city == null || city.isEmpty() ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("city"), city);
    }

    public static Specification<HouseForRent> priceGreaterThanOrEqual(Integer minPrice) {
        return (root, query, criteriaBuilder) ->
                minPrice == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<HouseForRent> priceLessThanOrEqual(Integer maxPrice) {
        return (root, query, criteriaBuilder) ->
                maxPrice == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<HouseForRent> roomsGreaterThanOrEqual(Integer minRooms) {
        return (root, query, criteriaBuilder) ->
                minRooms == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("rooms"), minRooms);
    }

    public static Specification<HouseForRent> areaGreaterThanOrEqual(Double minArea) {
        return (root, query, criteriaBuilder) ->
                minArea == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("area"), minArea);
    }

    public static Specification<HouseForRent> hasWifi(Boolean hasWifi) {
        return (root, query, criteriaBuilder) ->
                hasWifi == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("hasWifi"), hasWifi);
    }

    public static Specification<HouseForRent> hasParking(Boolean hasParking) {
        return (root, query, criteriaBuilder) ->
                hasParking == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("hasParking"), hasParking);
    }

    public static Specification<HouseForRent> hasPool(Boolean hasPool) {
        return (root, query, criteriaBuilder) ->
                hasPool == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("hasPool"), hasPool);
    }

    public static Specification<HouseForRent> titleContains(String keyword) {
        return (root, query, criteriaBuilder) ->
                keyword == null || keyword.isEmpty() ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                                "%" + keyword.toLowerCase() + "%");
    }

    public static Specification<HouseForRent> descriptionContains(String keyword) {
        return (root, query, criteriaBuilder) ->
                keyword == null || keyword.isEmpty() ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                                "%" + keyword.toLowerCase() + "%");
    }
}