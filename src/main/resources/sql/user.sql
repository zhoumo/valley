INSERT INTO `ROLE` (`ID`, `NAME`, `UPDATE_TIME`) VALUES (1, 'ROLE_USER', NOW());
INSERT INTO `ROLE` (`ID`, `NAME`, `UPDATE_TIME`) VALUES (2, 'ROLE_ADMIN', NOW());
INSERT INTO `ROLE` (`ID`, `NAME`, `UPDATE_TIME`) VALUES (3, 'ROLE_IT_ENGINEER', NOW());
INSERT INTO `ROLE` (`ID`, `NAME`, `UPDATE_TIME`) VALUES (4, 'ROLE_HR', NOW());
INSERT INTO `ROLE` (`ID`, `NAME`, `UPDATE_TIME`) VALUES (5, 'ROLE_HEADHUNTER', NOW());

INSERT INTO `USER` (`ID`, `LOGIN_NAME`, `REAL_NAME`, `PASSWORD`, `TYPE`, `UPDATE_TIME`) VALUES (1, 'admin@funshion.com', '管理员', '123456', 1, NOW());
INSERT INTO `USER` (`ID`, `LOGIN_NAME`, `REAL_NAME`, `PASSWORD`, `TYPE`, `UPDATE_TIME`) VALUES (2, 'user@funshion.com', '测试用户', '123456', 2, NOW());

INSERT INTO `ACCESS` (`USER_ID`, `ROLE_ID`) VALUES (1, 1);
INSERT INTO `ACCESS` (`USER_ID`, `ROLE_ID`) VALUES (1, 2);
INSERT INTO `ACCESS` (`USER_ID`, `ROLE_ID`) VALUES (2, 1);
INSERT INTO `ACCESS` (`USER_ID`, `ROLE_ID`) VALUES (2, 3);