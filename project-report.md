
# DAT250: Software Technology Experiment Assignment 2 - Hand-in Report

## Project Overview
Successfully implemented a REST API for a Poll application using Spring Boot with complete CRUD operations for Users, Polls, VoteOptions, and Votes. The application includes automated testing, API documentation, and CI/CD pipeline.

## Technical Problems Encountered and Solutions



### 1. **JSON Circular Reference Issues**
**Problem**: Infinite JSON looping when serializing objects with bidirectional relationships, causing stack overflow errors.

**Solution**: Added `@JsonIgnore` annotations to break circular references:
```java
@JsonIgnore
public List<Poll> getCreatedPolls() { return createdPolls; }
```

### 2. **Poll Deletion Not Cascading**
**Problem**: When deleting polls, associated votes and vote options remained in memory.

**Solution**: Implemented proper cascade deletion in `PollManager`:
```java
deleteVotesByPollId(String pollId);
deleteVoteOptionsByPollId(String pollId);
```

### 3. **Bidirectional Relationship Setup**
**Problem**: Vote options and votes not properly linked to their parent polls, causing deletion logic to fail.

**Solution**: Enhanced `createVoteOption` and `createVote` methods to properly establish bidirectional relationships.

### 4. **Test Automation Challenges**
**Problem**: Manual testing required copying IDs between requests, which was error-prone.

**Solution**: Implemented automated integration test using `TestRestTemplate` that automatically extracts and reuses IDs.

### 5. **GitHub Actions CI/CD Setup**
**Problem**: Initial CI failures due to missing Gradle wrapper files and test report path issues.

**Solution**: Used GitHub's Gradle action and simplified CI configuration:
```yaml
uses: gradle/actions/setup-gradle@v3
run: gradle test --stacktrace
```

### 6. **API Documentation Setup**
**Problem**: Basic Swagger documentation lacked organization and descriptions.

**Solution**: Added Springdoc OpenAPI with proper annotations:
```
@Tag(name = "Users", description = "User management APIs")
@Operation(summary = "Get all users", description = "Returns a list of all users")
```

## Test Scenario
My test scenario runs a total of 16 steps:
*  Step 1: List all users (empty at first)
*  Step 2: Create a new user (User 1)
*  Step 3: List all users (-> shows the newly created user)
*  Step 4: Create another user (User 2)
*  Step 5: List all users again (-> shows two users)
*  Step 6: User 1 creates a new poll
*  Step 7: List polls (-> shows the new poll)
*  Step 8: Create vote options for the poll
*  Step 9: User 2 votes on the poll (votes for Red)
*  Step 10: List votes (-> shows Bob's vote for Red)
*  Step 11: User 2 changes his vote (changes to Blue)
*  Step 12: List votes again (-> shows the most recent vote for User 2 - Blue)
*  Step 13: Delete the poll
*  Step 14: List votes again (-> should be empty since poll was deleted)
*  Step 15: Final check - List polls (should be empty)
*  Step 16: Final check - List users (should still have both users)

If you are testing this manually, you have to replace the id's in each instance. This is because each time you create a User, a Poll, a VoteOption or a Vote, new id's are being created for that instance.
If you are testing this automatically, you don't have to be careful about this because this is done automatically too.

You can see this code in `requests.http`

## Key Features Implemented

### ✅ Core Functionality
- User CRUD operations
- Poll CRUD operations with cascade deletion
- Vote option management
- Voting system with vote changing capability

### ✅ Testing
- Complete integration test covering the entire assignment scenario
- Automated CI/CD pipeline with GitHub Actions
- 16-step test scenario mirroring manual testing

### ✅ API Documentation
- Springdoc OpenAPI integration
- Swagger UI at http://localhost:8080/swagger-ui.html
- API Docs at http://localhost:8080/api-docs
- Organized endpoint documentation with descriptions

### ✅ Code Quality
- Proper package structure with separation of concerns
- Clean controller implementation
- Memory-based data management with `PollManager`
- Proper error handling and validation



---

## Conclusion
The assignment was successfully completed with all required functionality. The main challenges involved proper management of bidirectional relationships and setting up the CI/CD pipeline, but all issues were resolved through systematic debugging and implementation of best practices. I have learned a lot about of Spring Boot REST API development, testing methodologies, and DevOps practices.

