package com.company.jmixpm.security;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;


@RowLevelRole(name = "Restricted projects for notification",
        code = "restricted-projects")
public interface RestrictedProjectRole {
    @PredicateRowLevelPolicy(entityClass = Project.class,
            actions = {RowLevelPolicyAction.DELETE, RowLevelPolicyAction.UPDATE})
    default RowLevelBiPredicate<Project, ApplicationContext> allowOnlyManager() {
        return ((project, applicationContext) -> {
            CurrentAuthentication currentAuthentication = applicationContext.getBean(CurrentAuthentication.class);
            User user = (User) currentAuthentication.getUser();
            return user.equals(project.getManager());
        });
    }
}

