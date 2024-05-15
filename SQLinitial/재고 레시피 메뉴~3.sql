--drop--------------------
DROP TABLE Recipe;
DROP TABLE Menu ;
DROP TABLE Inventory;
DROP TABLE requestorder;
DROP TABLE irecipe;
DROP TABLE Ingredient;

-- 레시피 관리 테이블
CREATE TABLE Recipe (  
    recipe_content_id   VARCHAR2(5) NOT NULL, --pk 트리거 랜덤 아이디
    food_name             VARCHAR2(50) NOT NULL, --FK 메뉴 테이블과의 연결을 위한 외래 키
    ingredient_name     VARCHAR2(50) NOT NULL, --FK 제료 DB  
    recipe_quantity     NUMBER       NOT NULL
);

--재료로 재료 만들기 레시피 관리 테이블 
CREATE TABLE iRecipe (
    i_recipe_content_id VARCHAR2(100) NOT NULL, --pk 트리거r_ingredient_name+r_recipe_quantity
    r_ingredient_name   VARCHAR2(50) NOT NULL, --FK 제료 DB 입력 목록띄울때 where i% 입력시 프로그램에서 검사
    i_ingredient_name   VARCHAR2(50) NOT NULL, --FK 제료 DB  
    r_recipe_quantity   NUMBER       NOT NULL, --결과재료수
    i_recipe_quantity   NUMBER       NOT NULL  --사용재료수
);

-- 메뉴 테이블
CREATE TABLE Menu (
    food_name           VARCHAR2(50)    NOT NULL, --PK
    food_price          NUMBER          NOT NULL,
    food_description    VARCHAR2(201),  --(한글67글 띄어스기 3개당 1글자)
    food_category       VARCHAR2(15)    --(에피타이저 고기요리 생선요리 파스타 디저트)
);

-- 재료db
CREATE TABLE Ingredient (           
    ingredient_name            VARCHAR2(50) NOT NULL, --pk
    ingredient_unit            VARCHAR2(10) NOT NULL
);
    
-- 재고 관리 테이블
CREATE TABLE Inventory (
    inventory_id                VARCHAR2(100) NOT NULL,--pk 트리거 ingredients_name+expiry_date
    ingredient_name            VARCHAR2(50)   NOT NULL, --제료db fk 복수 존재 가능 유통기한별
    inventory_quantity          NUMBER        NOT NULL,
    expiry_date                 DATE         -- 없는 품목도 있음
);

--발주 요청 테이블 
CREATE TABLE requestOrder(
    request_id                  VARCHAR2(5) NOT NULL,  --pk 5자리수 랜덤 트리거
    ingredient_name             VARCHAR2(50) NOT NULL,
    request_quantity            NUMBER
);

--pk세팅----------------
ALTER TABLE Recipe
ADD CONSTRAINT Recipe_pk PRIMARY KEY (recipe_content_id);

ALTER TABLE Menu 
ADD CONSTRAINT Menu_pk PRIMARY KEY (food_name);

ALTER TABLE Ingredient 
ADD CONSTRAINT ingredient_pk PRIMARY KEY (ingredient_name);

ALTER TABLE Inventory 
ADD CONSTRAINT Inventory_pk PRIMARY KEY (inventory_id);

ALTER TABLE requestOrder 
ADD CONSTRAINT requestOrder_pk PRIMARY KEY (request_id);

ALTER TABLE iRecipe
ADD CONSTRAINT iRecipe_pk PRIMARY KEY (i_recipe_content_id);

--UK세팅-----------------
--ALTER TABLE Menu 
--ADD CONSTRAINT food_name_UK UNIQUE(food_name);

--ALTER TABLE Inventory
--ADD CONSTRAINT unique_name_date UNIQUE(inventory_id);

--FK세팅-----------------
-- 레시피-메뉴 외래 키 제약 조건 추가
ALTER TABLE Recipe
ADD CONSTRAINT fk_recipe_menu
FOREIGN KEY (food_name)
REFERENCES Menu(food_name);

-- 레시피-제료 외래 키 제약 조건 추가
ALTER TABLE Recipe
ADD CONSTRAINT fk_recipe_ingredient
FOREIGN KEY (ingredient_name)
REFERENCES Ingredient(ingredient_name);

--재료재료를 위한 레시피 결과재료
ALTER TABLE iRecipe
ADD CONSTRAINT fk_iRecipe_r_ingredient 
FOREIGN KEY (r_ingredient_name) 
REFERENCES Ingredient(ingredient_name); 
--CHECK (r_ingredient_name LIKE 'i%'); 프로그램에서 처리 

--재료재료를 위한 레시피 사용재료
ALTER TABLE iRecipe
ADD CONSTRAINT fk_iRecipe_i_ingredient 
FOREIGN KEY (i_ingredient_name) 
REFERENCES Ingredient(ingredient_name);


-- 재고-재료 외래 키 제약 조건 추가
ALTER TABLE Inventory
ADD CONSTRAINT fk_inventory_ingredient
FOREIGN KEY (ingredient_name)
REFERENCES Ingredient(ingredient_name);

-- 발주 요청-제료 외래 키 제약 조건 추가
ALTER TABLE RequestOrder
ADD CONSTRAINT fk_requestorder_ingredient
FOREIGN KEY (ingredient_name)
REFERENCES Ingredient(ingredient_name);

----트리거 세팅 
CREATE OR REPLACE TRIGGER generate_request_id
BEFORE INSERT ON requestOrder
FOR EACH ROW
BEGIN
    :NEW.request_id := DBMS_RANDOM.STRING('X', 5); -- 랜덤한 5자리 문자열 생성
END;
/
CREATE OR REPLACE TRIGGER generate_inventory_id
BEFORE INSERT ON Inventory
FOR EACH ROW
BEGIN
    :NEW.inventory_id:= DBMS_RANDOM.STRING('X', 5); -- 랜덤한 5자리 문자열 생성
END;
/
CREATE OR REPLACE TRIGGER generate_Recipe_content_id
BEFORE INSERT ON Recipe
FOR EACH ROW
BEGIN
    :NEW.recipe_content_id := DBMS_RANDOM.STRING('X', 5); -- 랜덤한 5자리 문자열 생성
END;
/
--서버에서 처리하는게 나을듯 
--함수 irecipe
--CREATE OR REPLACE FUNCTION generate_iRecipe_content_id(r_ingredient_name VARCHAR2, r_recipe_quantity NUMBER)
--RETURN VARCHAR2
--AS
--    v_i_recipe_content_id VARCHAR2(100);
--BEGIN
--    v_i_recipe_content_id := r_ingredient_name || TO_CHAR(r_recipe_quantity);
--    RETURN v_i_recipe_content_id;
--END;
--/




--초기값------------------


--join실험----------------
--SELECT * FROM Recipe JOIN Menu ON Recipe.food_name = Menu.food_name;


--실험용 초기값-------------