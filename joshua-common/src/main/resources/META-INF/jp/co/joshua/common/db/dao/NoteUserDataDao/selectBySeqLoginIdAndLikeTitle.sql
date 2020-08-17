SELECT
  *
FROM
  NOTE_USER_DATA
WHERE
  SEQ_LOGIN_ID = /* seqLoginId */1
  AND TITLE LIKE /* @infix(title) */'test'
ORDER BY
  UPDATE_DATE;
