package com.fdmgroup.beach_project.user_service.valueObject;

import com.fdmgroup.beach_project.user_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private User user;
    private Department department;


}
