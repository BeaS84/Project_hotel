package com.hotel.pethotel.service;

import com.hotel.pethotel.dto.AnimalDto;
import com.hotel.pethotel.mapper.AnimalMapper;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public List<AnimalModel> getAnimalList(){
        return animalRepository.findAll();
    }

    public List<AnimalModel> getAnimals(){
        return animalRepository.findAll();
    }

//    public AnimalDto addAnimal(AnimalDto animalDto){
//        AnimalModel animalModel = AnimalMapper.toAnimalModel(animalDto);
//        AnimalModel addAnimal = animalRepository.save(animalModel);
//        return AnimalMapper.toAnimalDto(addAnimal);
//    }


    public void addAnimal(AnimalModel animal) {
        System.out.println("Adding animal: " + animal);
        animalRepository.save(animal);
    }
}
