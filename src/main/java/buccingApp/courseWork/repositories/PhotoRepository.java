package buccingApp.courseWork.repositories;

import buccingApp.courseWork.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}