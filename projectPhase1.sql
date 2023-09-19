SELECT
    TABLE_NAME as 'Table Name',
    COLUMN_NAME as 'Column Name',
    REFERENCED_TABLE_NAME as 'Referenced Table',
    REFERENCED_COLUMN_NAME as 'Referenced Column'
FROM
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
    CONSTRAINT_SCHEMA = 'project';