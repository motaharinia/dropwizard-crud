UPDATE member
SET first_name= :firstName,
    last_name= :lastName,
    national_code= :nationalCode,
    date_of_birth= :dateOfBirth,
    setting_id= :settingId,
    hidden= :hidden,
    invalid= :invalid,
    update_at=NOW()
WHERE id = :id;