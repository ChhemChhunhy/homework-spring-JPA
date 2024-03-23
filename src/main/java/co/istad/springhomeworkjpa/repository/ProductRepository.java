package co.istad.springhomeworkjpa.repository;

import co.istad.springhomeworkjpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByIdAndStatus(Integer id, boolean status);
    Optional<Product> findByUuidAndStatus(String uuid, boolean status);
    boolean existsByName(String name);
    boolean existsByUuid(String uuid);
    void deleteByUuid(String uuid);

    Optional<Product> findByUuid(String uuid);

}
