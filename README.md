App uses spring boot framework, H2 db and flyway for db versioning.

run command:  mvn spring-boot:run

ENDPOINTS:
- proposal/sorted POST - get sorted proposals
- proposal POST - create proposal
- proposal/{proposalId} PUT - update proposal
- {proposalId}/history GET - get history for proposal

ASSUMPTIONS:
- when changing from VERIFIED to ACCEPTED you can't change text of proposal
- sorting is asc always
- no authentication or authorization
- dummy data can be inserted on app startup
- change state history stores previous and next state and date of change