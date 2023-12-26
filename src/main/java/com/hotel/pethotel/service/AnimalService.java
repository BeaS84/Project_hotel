package com.hotel.pethotel.service;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalModel getAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Zwierzę nie znalezione"));
    }

    public List<AnimalModel> getAnimalList(){
        return animalRepository.findAll();
    }

    public List<AnimalModel> getAnimals(){
        return animalRepository.findAll();
    }


    public void addAnimal(AnimalModel animal) {
        System.out.println("Adding animal: " + animal);
        animalRepository.save(animal);
    }


    public void saveEditAnimal(AnimalModel editAnimal) {
        animalRepository.save(editAnimal);
    }
//    public void deleteAnimal(Long animalId) { animalRepository.deleteById(animalId);
//    }

}
