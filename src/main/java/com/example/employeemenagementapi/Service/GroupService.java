package com.example.employeemenagementapi.Service;

import com.example.employeemenagementapi.Model.ClassEmployee;
import com.example.employeemenagementapi.Model.Employee;
import com.example.employeemenagementapi.Model.Rating;
import com.example.employeemenagementapi.Repository.GroupRepository;
import com.example.employeemenagementapi.Repository.RatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final RatingRepository ratingRepository;

    public GroupService(GroupRepository groupRepository, RatingRepository ratingRepository) {
        this.groupRepository = groupRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<ClassEmployee> getAllGroups() {
        return groupRepository.findAll();
    }

    public ClassEmployee addGroup(ClassEmployee group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("Grupa o ID " + id + " nie istnieje.");
        }
        groupRepository.deleteById(id);
    }

    public List<Employee> getEmployeesInGroup(Long id) {
        ClassEmployee group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupa o ID " + id + " nie istnieje."));
        return group.getEmployees();
    }

    public double getGroupFillPercentage(Long id) {
        ClassEmployee group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupa o ID " + id + " nie istnieje."));
        return group.getZapelnienie();
    }

    public Rating addRating(Long groupId, int ocena) {
        ClassEmployee group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Grupa o ID " + groupId + " nie istnieje."));

        Rating rating = new Rating(ocena, group);
        return ratingRepository.save(rating);
    }
}