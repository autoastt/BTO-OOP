package entity.request;

// Request class
public class Request {
    // Attributes
    private String requestID;
    private RequestType requestType;
    private String userID;
    private String projectID;
    private RequestStatus requestStatus;
    
    // Constructor (not specified in UML, but adding a basic one for completeness)

    public Request(){
        this.requestID = "";
        this.requestType = RequestType.NONE;
        this.userID = "";
        this.projectID = "";
        this.requestStatus = RequestStatus.PENDING;
    }
    
    public Request(String requestID, RequestType requestType, String userID, String projectID, RequestStatus requestStatus) {
        this.requestID = requestID;
        this.requestType = requestType;
        this.userID = userID;
        this.projectID = projectID;
        this.requestStatus = requestStatus;
    }

    // Getter for requestID
    public String getRequestID() {
        return requestID;
    }

    // Setter for requestID
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    // Getter for requestType
    public RequestType getRequestType() {
        return requestType;
    }

    // Setter for requestType
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    // Getter for userID
    public String getUserID() {
        return userID;
    }

    // Setter for userID
    public void setUserID(String userID) {
        this.userID = userID;
    }

    // Getter for projectID
    public String getProjectID() {
        return projectID;
    }

    // Setter for projectID
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    // Getter for requestStatus
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    // Setter for requestStatus
    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
