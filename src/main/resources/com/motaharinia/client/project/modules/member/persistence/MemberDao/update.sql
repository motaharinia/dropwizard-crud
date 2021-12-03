UPDATE member
SET first_name= :firstName,
    last_name= :lastName,
    national_code= :nationalCode,
    date_of_birth= :dateOfBirth
WHERE id = :id;