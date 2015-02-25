-- 상품
DROP TABLE IF EXISTS `GOODS` RESTRICT;

-- 등록거래처
DROP TABLE IF EXISTS `MATCHING` RESTRICT;

-- 거래처
DROP TABLE IF EXISTS `CLIENT` RESTRICT;

-- 주문리스트
DROP TABLE IF EXISTS `ORDERLIST` RESTRICT;

-- 납품업체
DROP TABLE IF EXISTS `SUPPLIER` RESTRICT;

-- 주문총액관리
DROP TABLE IF EXISTS `ORDERTOTAL` RESTRICT;

-- 관리자
DROP TABLE IF EXISTS `ADMINSTER` RESTRICT;

-- 상품
CREATE TABLE `GOODS` (
	`GNO`      INTEGER(8)   NOT NULL COMMENT '상품일련번호', -- 상품일련번호
	`GCODE`    VARCHAR(255) NOT NULL COMMENT '상품코드', -- 상품코드
	`SNO`      INTEGER(8)   NOT NULL COMMENT '납품업체일련번호', -- 납품업체일련번호
	`GNAME`    VARCHAR(15)  NOT NULL COMMENT '상품명', -- 상품명
	`GIMG`     VARCHAR(255) NULL     COMMENT '이미지', -- 이미지
	`GUNIT`    VARCHAR(10)  NOT NULL COMMENT '단위', -- 단위
	`GCAT`     VARCHAR(10)  NULL     COMMENT '분류', -- 분류
	`GMEMO`    VARCHAR(255) NULL     COMMENT '메모', -- 메모
	`GPRICE_A` INTEGER      NOT NULL COMMENT 'A등급가격', -- A등급가격
	`GPRICE_B` INTEGER      NOT NULL COMMENT 'B등급가격', -- B등급가격
	`GPRICE_C` INTEGER      NOT NULL COMMENT 'C등급가격' -- C등급가격
)
COMMENT '상품';

-- 상품
ALTER TABLE `GOODS`
	ADD CONSTRAINT `PK_GOODS` -- 상품 기본키
		PRIMARY KEY (
			`GCODE` -- 상품코드
		);

-- 상품 유니크 인덱스
CREATE UNIQUE INDEX `UIX_GOODS`
	ON `GOODS` ( -- 상품
		`GNO` ASC -- 상품일련번호
	);

ALTER TABLE `GOODS`
	MODIFY COLUMN `GNO` INTEGER(8) NOT NULL AUTO_INCREMENT COMMENT '상품일련번호';

-- 등록거래처
CREATE TABLE `MATCHING` (
	`CNO`    INTEGER(8) NOT NULL COMMENT '거래처일련번호', -- 거래처일련번호
	`SNO`    INTEGER(8) NOT NULL COMMENT '납품업체일련번호', -- 납품업체일련번호
	`MLEVEL` VARCHAR(2) NULL     COMMENT '거래처등급', -- 거래처등급
	`MDATE`  DATE       NULL     COMMENT '등록일' -- 등록일
)
COMMENT '등록거래처';

-- 등록거래처
ALTER TABLE `MATCHING`
	ADD CONSTRAINT `PK_MATCHING` -- 등록거래처 기본키
		PRIMARY KEY (
			`CNO`, -- 거래처일련번호
			`SNO`  -- 납품업체일련번호
		);

-- 거래처
CREATE TABLE `CLIENT` (
	`CNO`       INTEGER(8)   NOT NULL COMMENT '거래처일련번호', -- 거래처일련번호
	`CTEL`      VARCHAR(30)  NOT NULL COMMENT '거래처전화', -- 거래처전화
	`CPWD`      VARCHAR(20)  NOT NULL COMMENT '거래처PWD', -- 거래처PWD
	`CCNAME`    VARCHAR(15)  NOT NULL COMMENT '거래처명', -- 거래처명
	`CMAIL`     VARCHAR(40)  NULL     COMMENT '이메일', -- 이메일
	`CNAME`     VARCHAR(15)  NULL     COMMENT '대표자', -- 대표자
	`CPOSTNO`   VARCHAR(255) NULL     COMMENT '우편번호', -- 우편번호
	`CADDR`     VARCHAR(255) NULL     COMMENT '기본주소', -- 기본주소
	`CADDR_DET` VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
	`CMEMO`     VARCHAR(255) NULL     COMMENT '메모' -- 메모
)
COMMENT '거래처';

-- 거래처
ALTER TABLE `CLIENT`
	ADD CONSTRAINT `PK_CLIENT` -- 거래처 기본키
		PRIMARY KEY (
			`CNO` -- 거래처일련번호
		);

-- 거래처 유니크 인덱스
CREATE UNIQUE INDEX `UIX_CLIENT`
	ON `CLIENT` ( -- 거래처
		`CTEL` ASC -- 거래처전화
	);

ALTER TABLE `CLIENT`
	MODIFY COLUMN `CNO` INTEGER(8) NOT NULL AUTO_INCREMENT COMMENT '거래처일련번호';

-- 주문리스트
CREATE TABLE `ORDERLIST` (
	`ONO`       INTEGER(8)   NOT NULL COMMENT '주문일련번호', -- 주문일련번호
	`SNO`       INTEGER(8)   NOT NULL COMMENT '납품업체일련번호', -- 납품업체일련번호
	`CNO`       INTEGER(8)   NOT NULL COMMENT '거래처일련번호', -- 거래처일련번호
	`ODATE`     DATE         NOT NULL COMMENT '주문일', -- 주문일
	`OPRICE`    INTEGER      NULL     COMMENT '상품가격', -- 상품가격
	`OQTY`      INTEGER      NOT NULL COMMENT '수량', -- 수량
	`ORDATE`    DATE         NOT NULL COMMENT '출고일', -- 출고일
	`ODEL_STAT` VARCHAR(10)  NULL     COMMENT '배송상태', -- 배송상태
	`OMEMO`     VARCHAR(255) NULL     COMMENT '메모', -- 메모
	`GCODE`     VARCHAR(255) NOT NULL     COMMENT '상품코드' -- 상품코드
)
COMMENT '주문리스트';

-- 주문리스트
ALTER TABLE `ORDERLIST`
	ADD CONSTRAINT `PK_ORDERLIST` -- 주문리스트 기본키
		PRIMARY KEY (
			`ONO` -- 주문일련번호
		);

ALTER TABLE `ORDERLIST`
	MODIFY COLUMN `ONO` INTEGER(8) NOT NULL AUTO_INCREMENT COMMENT '주문일련번호';

-- 납품업체
CREATE TABLE `SUPPLIER` (
	`SNO`       INTEGER(8)   NOT NULL COMMENT '납품업체일련번호', -- 납품업체일련번호
	`SID`       VARCHAR(20)  NOT NULL COMMENT '아이디', -- 아이디
	`SPWD`      VARCHAR(20)  NOT NULL COMMENT '암호', -- 암호
	`STEL`      VARCHAR(30)  NOT NULL COMMENT '회사전화', -- 회사전화
	`SCNAME`    VARCHAR(20)  NOT NULL COMMENT '업체명', -- 업체명
	`SNAME`     VARCHAR(15)  NOT NULL COMMENT '대표자명', -- 대표자명
	`SBNO`      VARCHAR(20)  NOT NULL COMMENT '사업등록번호', -- 사업등록번호
	`SPOSTNO`   VARCHAR(255) NULL     COMMENT '우편번호', -- 우편번호
	`SADDR`     VARCHAR(255) NULL     COMMENT '기본주소', -- 기본주소
	`SADDR_DET` VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
	`SMEMO`     VARCHAR(255) NULL     COMMENT '회사소개', -- 회사소개
	`AID`       VARCHAR(20)  NULL     COMMENT '아이디2' -- 아이디2
)
COMMENT '납품업체';

-- 납품업체
ALTER TABLE `SUPPLIER`
	ADD CONSTRAINT `PK_SUPPLIER` -- 납품업체 기본키
		PRIMARY KEY (
			`SNO` -- 납품업체일련번호
		);

-- 납품업체 유니크 인덱스
CREATE UNIQUE INDEX `UIX_SUPPLIER`
	ON `SUPPLIER` ( -- 납품업체
		`SID` ASC -- 아이디
	);

-- 납품업체 유니크 인덱스2
CREATE UNIQUE INDEX `UIX_SUPPLIER2`
	ON `SUPPLIER` ( -- 납품업체
		`STEL` ASC -- 회사전화
	);

ALTER TABLE `SUPPLIER`
	MODIFY COLUMN `SNO` INTEGER(8) NOT NULL AUTO_INCREMENT COMMENT '납품업체일련번호';

-- 주문총액관리
CREATE TABLE `ORDERTOTAL` (
	`CNO`       INTEGER(8) NOT NULL COMMENT '거래처일련번호', -- 거래처일련번호
	`SNO`       INTEGER(8) NOT NULL COMMENT '납품업체일련번호', -- 납품업체일련번호
	`TOT_MONTH` DATE       NOT NULL COMMENT '년월', -- 년월
	`TOT_PRICE` INTEGER    NULL     COMMENT '총액' -- 총액
)
COMMENT '주문총액관리';

-- 주문총액관리
ALTER TABLE `ORDERTOTAL`
	ADD CONSTRAINT `PK_ORDERTOTAL` -- 주문총액관리 기본키
		PRIMARY KEY (
			`CNO`,       -- 거래처일련번호
			`SNO`,       -- 납품업체일련번호
			`TOT_MONTH`  -- 년월
		);

-- 관리자
CREATE TABLE `ADMINSTER` (
	`AID`   VARCHAR(20) NOT NULL COMMENT '아이디', -- 아이디
	`APWD`  VARCHAR(20) NOT NULL COMMENT '비밀번호', -- 비밀번호
	`ANAME` VARCHAR(15) NOT NULL COMMENT '이름', -- 이름
	`ATEL`  VARCHAR(30) NOT NULL COMMENT '전화번호' -- 전화번호
)
COMMENT '관리자';

-- 관리자
ALTER TABLE `ADMINSTER`
	ADD CONSTRAINT `PK_ADMINSTER` -- 관리자 기본키
		PRIMARY KEY (
			`AID` -- 아이디
		);

-- 관리자 유니크 인덱스
CREATE UNIQUE INDEX `UIX_ADMINSTER`
	ON `ADMINSTER` ( -- 관리자
		`ATEL` ASC -- 전화번호
	);

-- 상품
ALTER TABLE `GOODS`
	ADD CONSTRAINT `FK_SUPPLIER_TO_GOODS` -- 납품업체 -> 상품
		FOREIGN KEY (
			`SNO` -- 납품업체일련번호
		)
		REFERENCES `SUPPLIER` ( -- 납품업체
			`SNO` -- 납품업체일련번호
		);

-- 등록거래처
ALTER TABLE `MATCHING`
	ADD CONSTRAINT `FK_CLIENT_TO_MATCHING` -- 거래처 -> 등록거래처
		FOREIGN KEY (
			`CNO` -- 거래처일련번호
		)
		REFERENCES `CLIENT` ( -- 거래처
			`CNO` -- 거래처일련번호
		);

-- 등록거래처
ALTER TABLE `MATCHING`
	ADD CONSTRAINT `FK_SUPPLIER_TO_MATCHING` -- 납품업체 -> 등록거래처
		FOREIGN KEY (
			`SNO` -- 납품업체일련번호
		)
		REFERENCES `SUPPLIER` ( -- 납품업체
			`SNO` -- 납품업체일련번호
		);

-- 주문리스트
ALTER TABLE `ORDERLIST`
	ADD CONSTRAINT `FK_MATCHING_TO_ORDERLIST` -- 등록거래처 -> 주문리스트
		FOREIGN KEY (
			`CNO`, -- 거래처일련번호
			`SNO`  -- 납품업체일련번호
		)
		REFERENCES `MATCHING` ( -- 등록거래처
			`CNO`, -- 거래처일련번호
			`SNO`  -- 납품업체일련번호
		);

-- 주문리스트
ALTER TABLE `ORDERLIST`
	ADD CONSTRAINT `FK_GOODS_TO_ORDERLIST` -- 상품 -> 주문리스트
		FOREIGN KEY (
			`GCODE` -- 상품코드
		)
		REFERENCES `GOODS` ( -- 상품
			`GCODE` -- 상품코드
		);

-- 납품업체
ALTER TABLE `SUPPLIER`
	ADD CONSTRAINT `FK_ADMINSTER_TO_SUPPLIER` -- 관리자 -> 납품업체
		FOREIGN KEY (
			`AID` -- 아이디2
		)
		REFERENCES `ADMINSTER` ( -- 관리자
			`AID` -- 아이디
		);

-- 주문총액관리
ALTER TABLE `ORDERTOTAL`
	ADD CONSTRAINT `FK_MATCHING_TO_ORDERTOTAL` -- 등록거래처 -> 주문총액관리
		FOREIGN KEY (
			`CNO`, -- 거래처일련번호
			`SNO`  -- 납품업체일련번호
		)
		REFERENCES `MATCHING` ( -- 등록거래처
			`CNO`, -- 거래처일련번호
			`SNO`  -- 납품업체일련번호
		);