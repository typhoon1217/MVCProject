--drop--------------------
DROP TABLE Recipe;
DROP TABLE Menu ;
DROP TABLE Inventory;
DROP TABLE requestorder;
DROP TABLE irecipe;
DROP TABLE Ingredient;

-- ������ ���� ���̺�
CREATE TABLE Recipe (  
    recipe_content_id   VARCHAR2(5) NOT NULL, --pk Ʈ���� ���� ���̵�
    food_name             VARCHAR2(50) NOT NULL, --FK �޴� ���̺���� ������ ���� �ܷ� Ű
    ingredient_name     VARCHAR2(50) NOT NULL, --FK ���� DB  
    recipe_quantity     NUMBER       NOT NULL
);

--���� ��� ����� ������ ���� ���̺� 
CREATE TABLE iRecipe (
    i_recipe_content_id VARCHAR2(100) NOT NULL, --pk Ʈ����r_ingredient_name+r_recipe_quantity
    r_ingredient_name   VARCHAR2(50) NOT NULL, --FK ���� DB �Է� ��϶�ﶧ where i% �Է½� ���α׷����� �˻�
    i_ingredient_name   VARCHAR2(50) NOT NULL, --FK ���� DB  
    r_recipe_quantity   NUMBER       NOT NULL, --�������
    i_recipe_quantity   NUMBER       NOT NULL  --�������
);

-- �޴� ���̺�
CREATE TABLE Menu (
    food_name           VARCHAR2(50)    NOT NULL, --PK
    food_price          NUMBER          NOT NULL,
    food_description    VARCHAR2(201),  --(�ѱ�67�� ���� 3���� 1����)
    food_category       VARCHAR2(15)    --(����Ÿ���� ���丮 �����丮 �Ľ�Ÿ ����Ʈ)
);

-- ���db
CREATE TABLE Ingredient (           
    ingredient_name            VARCHAR2(50) NOT NULL, --pk
    ingredient_unit            VARCHAR2(10) NOT NULL
);
    
-- ��� ���� ���̺�
CREATE TABLE Inventory (
    inventory_id                VARCHAR2(100) NOT NULL,--pk Ʈ���� ingredients_name+expiry_date
    ingredient_name            VARCHAR2(50)   NOT NULL, --����db fk ���� ���� ���� ������Ѻ�
    inventory_quantity          NUMBER        NOT NULL,
    expiry_date                 DATE         -- ���� ǰ�� ����
);

--���� ��û ���̺� 
CREATE TABLE requestOrder(
    request_id                  VARCHAR2(5) NOT NULL,  --pk 5�ڸ��� ���� Ʈ����
    ingredient_name             VARCHAR2(50) NOT NULL,
    request_quantity            NUMBER
);

--pk����----------------
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

--UK����-----------------
--ALTER TABLE Menu 
--ADD CONSTRAINT food_name_UK UNIQUE(food_name);

--ALTER TABLE Inventory
--ADD CONSTRAINT unique_name_date UNIQUE(inventory_id);

--FK����-----------------
-- ������-�޴� �ܷ� Ű ���� ���� �߰�
ALTER TABLE Recipe
ADD CONSTRAINT fk_recipe_menu
FOREIGN KEY (food_name)
REFERENCES Menu(food_name);

-- ������-���� �ܷ� Ű ���� ���� �߰�
ALTER TABLE Recipe
ADD CONSTRAINT fk_recipe_ingredient
FOREIGN KEY (ingredient_name)
REFERENCES Ingredient(ingredient_name);

--�����Ḧ ���� ������ ������
ALTER TABLE iRecipe
ADD CONSTRAINT fk_iRecipe_r_ingredient 
FOREIGN KEY (r_ingredient_name) 
REFERENCES Ingredient(ingredient_name); 
--CHECK (r_ingredient_name LIKE 'i%'); ���α׷����� ó�� 

--�����Ḧ ���� ������ ������
ALTER TABLE iRecipe
ADD CONSTRAINT fk_iRecipe_i_ingredient 
FOREIGN KEY (i_ingredient_name) 
REFERENCES Ingredient(ingredient_name);


-- ���-��� �ܷ� Ű ���� ���� �߰�
ALTER TABLE Inventory
ADD CONSTRAINT fk_inventory_ingredient
FOREIGN KEY (ingredient_name)
REFERENCES Ingredient(ingredient_name);

-- ���� ��û-���� �ܷ� Ű ���� ���� �߰�
ALTER TABLE RequestOrder
ADD CONSTRAINT fk_requestorder_ingredient
FOREIGN KEY (ingredient_name)
REFERENCES Ingredient(ingredient_name);

----Ʈ���� ���� 
CREATE OR REPLACE TRIGGER generate_request_id
BEFORE INSERT ON requestOrder
FOR EACH ROW
BEGIN
    :NEW.request_id := DBMS_RANDOM.STRING('X', 5); -- ������ 5�ڸ� ���ڿ� ����
END;
/
CREATE OR REPLACE TRIGGER generate_inventory_id
BEFORE INSERT ON Inventory
FOR EACH ROW
BEGIN
    :NEW.inventory_id:= DBMS_RANDOM.STRING('X', 5); -- ������ 5�ڸ� ���ڿ� ����
END;
/
CREATE OR REPLACE TRIGGER generate_Recipe_content_id
BEFORE INSERT ON Recipe
FOR EACH ROW
BEGIN
    :NEW.recipe_content_id := DBMS_RANDOM.STRING('X', 5); -- ������ 5�ڸ� ���ڿ� ����
END;
/
--�������� ó���ϴ°� ������ 
--�Լ� irecipe
--CREATE OR REPLACE FUNCTION generate_iRecipe_content_id(r_ingredient_name VARCHAR2, r_recipe_quantity NUMBER)
--RETURN VARCHAR2
--AS
--    v_i_recipe_content_id VARCHAR2(100);
--BEGIN
--    v_i_recipe_content_id := r_ingredient_name || TO_CHAR(r_recipe_quantity);
--    RETURN v_i_recipe_content_id;
--END;
--/




--�ʱⰪ------------------


--join����----------------
--SELECT * FROM Recipe JOIN Menu ON Recipe.food_name = Menu.food_name;


--����� �ʱⰪ-------------