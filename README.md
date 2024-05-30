```mermaid
erDiagram
    User ||--o{ Schedule : 
    Schedule ||--o{ Comment : 

    User {
        Long id
        String username
        String nickname
        String password
        UserRoleEnum role
        LocalDateTime createdAt
    }

    Schedule {
        Long id
        String title
        String contents
        String admin
        String password
        LocalDateTime createdAt
    }

    Comment {
        Long id
        String content
        String username
        Long scheduleId
        LocalDateTime createdAt
    }


```
