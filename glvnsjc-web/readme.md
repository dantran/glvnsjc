
#Story add ability to manage teacher certificates

## DB Changes

  * Add Certificate model which can be attached to a teacher via a collection Set

## UI Changes

  * Add CertificateView to

  * Teach Add case: will not add ability to add certificate into a blank teacher from.  Just dont see a need for it
    User will need to create teacher record first

  * Update case, this is where we will all teacher form to add/update/delete certificate list

  * Delete case: make sure to remove both teacher and certifacte before to it