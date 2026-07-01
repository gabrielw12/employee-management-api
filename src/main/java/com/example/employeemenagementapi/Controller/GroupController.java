package com.example.employeemenagementapi.Controller;

import com.example.employeemenagementapi.Model.ClassEmployee;
import com.example.employeemenagementapi.Model.Employee;
import com.example.employeemenagementapi.Model.Rating;
import com.example.employeemenagementapi.Service.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group") // GET: /api/group [cite: 8]
    public ResponseEntity<List<ClassEmployee>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @PostMapping("/group") // POST: /api/group [cite: 9]
    public ResponseEntity<ClassEmployee> addGroup(@Valid @RequestBody ClassEmployee group) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.addGroup(group));
    }

    @DeleteMapping("/group/{id}") // DELETE: /api/group/:id [cite: 10]
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{id}/employee") // GET: /api/group/:id/employee [cite: 11]
    public ResponseEntity<List<Employee>> getEmployeesInGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getEmployeesInGroup(id));
    }

    @GetMapping("/group/{id}/fill") // GET: /api/group/:id/fill [cite: 12]
    public ResponseEntity<Map<String, Double>> getGroupFill(@PathVariable Long id) {
        double fill = groupService.getGroupFillPercentage(id);
        return ResponseEntity.ok(Map.of("zapelnienie_procentowe", fill));
    }

    @PostMapping("/rating") // POST: /api/rating [cite: 13]
    public ResponseEntity<Rating> addRating(@RequestBody Map<String, Object> payload) {
        Long groupId = Long.valueOf(payload.get("groupId").toString());
        Integer ocena = Integer.valueOf(payload.get("ocena").toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.addRating(groupId, ocena));
    }
}