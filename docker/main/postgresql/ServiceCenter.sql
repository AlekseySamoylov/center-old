--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE alekseysamoylov;
ALTER ROLE alekseysamoylov WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'md5d7980ace5b59ea073a4c25bd767b9c8d';




--
-- Database creation
--

CREATE DATABASE alekseysamoylov WITH TEMPLATE = template0 OWNER = alekseysamoylov;
CREATE DATABASE nastushenka WITH TEMPLATE = template0 OWNER = alekseysamoylov;
CREATE DATABASE note WITH TEMPLATE = template0 OWNER = alekseysamoylov;
CREATE DATABASE servicecenter WITH TEMPLATE = template0 OWNER = alekseysamoylov;
REVOKE ALL ON DATABASE template1 FROM PUBLIC;
REVOKE ALL ON DATABASE template1 FROM postgres;
GRANT ALL ON DATABASE template1 TO postgres;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


\connect alekseysamoylov

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account_car; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.account_car (
    account_car_id bigint NOT NULL,
    account_car_number character varying(255),
    car_model_id bigint,
    account_id bigint
);


ALTER TABLE public.account_car OWNER TO alekseysamoylov;

--
-- Name: account_car_account_car_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.account_car_account_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_car_account_car_id_seq OWNER TO alekseysamoylov;

--
-- Name: account_car_account_car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.account_car_account_car_id_seq OWNED BY public.account_car.account_car_id;


--
-- Name: car_mark; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_mark (
    car_mark_id bigint NOT NULL,
    car_mark_name character varying(255)
);


ALTER TABLE public.car_mark OWNER TO alekseysamoylov;

--
-- Name: car_model; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_model (
    car_model_id bigint NOT NULL,
    car_model_name character varying(255),
    car_mark_id bigint
);


ALTER TABLE public.car_model OWNER TO alekseysamoylov;

--
-- Name: car_model_repair_advice; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_model_repair_advice (
    car_repair_advice_id bigint NOT NULL,
    car_model_id bigint NOT NULL
);


ALTER TABLE public.car_model_repair_advice OWNER TO alekseysamoylov;

--
-- Name: car_model_repair_price; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_model_repair_price (
    car_repair_price_id bigint NOT NULL,
    car_model_id bigint NOT NULL
);


ALTER TABLE public.car_model_repair_price OWNER TO alekseysamoylov;

--
-- Name: car_model_repair_section; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_model_repair_section (
    car_repair_section_id bigint NOT NULL,
    car_model_id bigint NOT NULL
);


ALTER TABLE public.car_model_repair_section OWNER TO alekseysamoylov;

--
-- Name: car_repair_account; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_repair_account (
    car_repair_account_id bigint NOT NULL,
    car_repair_account_name character varying(255),
    car_repair_account_password character varying(255),
    car_repair_account_role_id bigint
);


ALTER TABLE public.car_repair_account OWNER TO alekseysamoylov;

--
-- Name: car_repair_account_car_repair_account_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.car_repair_account_car_repair_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_repair_account_car_repair_account_id_seq OWNER TO alekseysamoylov;

--
-- Name: car_repair_account_car_repair_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.car_repair_account_car_repair_account_id_seq OWNED BY public.car_repair_account.car_repair_account_id;


--
-- Name: car_repair_account_property; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_repair_account_property (
    account_property_id bigint NOT NULL,
    discount integer,
    last_name character varying(255),
    first_name character varying(255),
    other character varying(255),
    phone_number character varying(255),
    phone_number_other character varying(255)
);


ALTER TABLE public.car_repair_account_property OWNER TO alekseysamoylov;

--
-- Name: car_repair_account_role; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_repair_account_role (
    car_repair_account_role_id bigint NOT NULL,
    car_repair_account_role_name character varying(255)
);


ALTER TABLE public.car_repair_account_role OWNER TO alekseysamoylov;

--
-- Name: car_repair_advice; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_repair_advice (
    car_repair_advice_id bigint NOT NULL,
    car_repair_advice_text character varying(255),
    car_repair_advice_name character varying(255),
    car_repair_section_id bigint
);


ALTER TABLE public.car_repair_advice OWNER TO alekseysamoylov;

--
-- Name: car_repair_advice_car_repair_advice_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.car_repair_advice_car_repair_advice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_repair_advice_car_repair_advice_id_seq OWNER TO alekseysamoylov;

--
-- Name: car_repair_advice_car_repair_advice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.car_repair_advice_car_repair_advice_id_seq OWNED BY public.car_repair_advice.car_repair_advice_id;


--
-- Name: car_repair_price; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_repair_price (
    car_repair_price_id bigint NOT NULL,
    car_repair_price_name character varying(255),
    car_repair_price_description character varying(255),
    car_repair_price_price double precision,
    car_repair_account_id bigint,
    car_repair_advice_id bigint,
    car_repair_section_id bigint
);


ALTER TABLE public.car_repair_price OWNER TO alekseysamoylov;

--
-- Name: car_repair_price_car_repair_price_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.car_repair_price_car_repair_price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_repair_price_car_repair_price_id_seq OWNER TO alekseysamoylov;

--
-- Name: car_repair_price_car_repair_price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.car_repair_price_car_repair_price_id_seq OWNED BY public.car_repair_price.car_repair_price_id;


--
-- Name: car_repair_section; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.car_repair_section (
    car_repair_section_id bigint NOT NULL,
    car_repair_section_name character varying(255)
);


ALTER TABLE public.car_repair_section OWNER TO alekseysamoylov;

--
-- Name: company_account; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.company_account (
    company_id bigint NOT NULL,
    account_id bigint NOT NULL
);


ALTER TABLE public.company_account OWNER TO alekseysamoylov;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO alekseysamoylov;

--
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    next_val bigint
);


ALTER TABLE public.hibernate_sequences OWNER TO alekseysamoylov;

--
-- Name: mark_index_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.mark_index_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mark_index_seq OWNER TO alekseysamoylov;

--
-- Name: order_part; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.order_part (
    order_part_id bigint NOT NULL,
    o_p_name character varying(255),
    o_p_price double precision,
    o_p_sum double precision,
    o_p_value integer,
    order_id bigint
);


ALTER TABLE public.order_part OWNER TO alekseysamoylov;

--
-- Name: order_part_order_part_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.order_part_order_part_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_part_order_part_id_seq OWNER TO alekseysamoylov;

--
-- Name: order_part_order_part_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.order_part_order_part_id_seq OWNED BY public.order_part.order_part_id;


--
-- Name: order_rating; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.order_rating (
    id bigint NOT NULL,
    comment character varying(255),
    rating integer,
    complete boolean DEFAULT false
);


ALTER TABLE public.order_rating OWNER TO alekseysamoylov;

--
-- Name: order_work; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.order_work (
    order_work_id bigint NOT NULL,
    o_w_name character varying(255),
    o_w_price double precision,
    o_w_sum double precision,
    o_w_value integer,
    master_account_id bigint,
    order_id bigint
);


ALTER TABLE public.order_work OWNER TO alekseysamoylov;

--
-- Name: order_work_order_work_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.order_work_order_work_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_work_order_work_id_seq OWNER TO alekseysamoylov;

--
-- Name: order_work_order_work_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.order_work_order_work_id_seq OWNED BY public.order_work.order_work_id;


--
-- Name: part_store; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.part_store (
    part_store_id bigint NOT NULL,
    part_store_name character varying(255),
    part_store_price double precision,
    part_store_value integer,
    part_type_id bigint
);


ALTER TABLE public.part_store OWNER TO alekseysamoylov;

--
-- Name: part_store_part_store_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.part_store_part_store_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.part_store_part_store_id_seq OWNER TO alekseysamoylov;

--
-- Name: part_store_part_store_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.part_store_part_store_id_seq OWNED BY public.part_store.part_store_id;


--
-- Name: part_type; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.part_type (
    part_type_id bigint NOT NULL,
    part_type_name character varying(255)
);


ALTER TABLE public.part_type OWNER TO alekseysamoylov;

--
-- Name: part_type_part_type_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.part_type_part_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.part_type_part_type_id_seq OWNER TO alekseysamoylov;

--
-- Name: part_type_part_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.part_type_part_type_id_seq OWNED BY public.part_type.part_type_id;


--
-- Name: purchase_log; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.purchase_log (
    purchase_log_id bigint NOT NULL,
    purchase_log_date date,
    purchase_log_text character varying(255)
);


ALTER TABLE public.purchase_log OWNER TO alekseysamoylov;

--
-- Name: purchase_log_purchase_log_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.purchase_log_purchase_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchase_log_purchase_log_id_seq OWNER TO alekseysamoylov;

--
-- Name: purchase_log_purchase_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.purchase_log_purchase_log_id_seq OWNED BY public.purchase_log.purchase_log_id;


--
-- Name: repair_company; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.repair_company (
    company_id bigint NOT NULL,
    city character varying(255),
    country character varying(255),
    district character varying(255),
    email character varying(255),
    house character varying(255),
    company_name character varying(255),
    other character varying(255),
    phone character varying(255),
    site character varying(255),
    street character varying(255)
);


ALTER TABLE public.repair_company OWNER TO alekseysamoylov;

--
-- Name: repair_company_company_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.repair_company_company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.repair_company_company_id_seq OWNER TO alekseysamoylov;

--
-- Name: repair_company_company_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.repair_company_company_id_seq OWNED BY public.repair_company.company_id;


--
-- Name: repair_order; Type: TABLE; Schema: public; Owner: alekseysamoylov
--

CREATE TABLE public.repair_order (
    order_id bigint NOT NULL,
    client_car_number character varying(255),
    discount integer,
    order_description text,
    order_advice character varying(255),
    order_complete boolean,
    order_date date,
    order_prepayment double precision,
    order_sum double precision,
    client_account_id bigint,
    client_car_model bigint,
    company_id bigint,
    manager_account_id bigint,
    master_account_id bigint,
    parts_discount boolean,
    parts_sum double precision,
    works_discount boolean,
    works_sum double precision
);


ALTER TABLE public.repair_order OWNER TO alekseysamoylov;

--
-- Name: repair_order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: alekseysamoylov
--

CREATE SEQUENCE public.repair_order_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.repair_order_order_id_seq OWNER TO alekseysamoylov;

--
-- Name: repair_order_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alekseysamoylov
--

ALTER SEQUENCE public.repair_order_order_id_seq OWNED BY public.repair_order.order_id;


--
-- Name: account_car_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.account_car ALTER COLUMN account_car_id SET DEFAULT nextval('public.account_car_account_car_id_seq'::regclass);


--
-- Name: car_repair_account_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.car_repair_account ALTER COLUMN car_repair_account_id SET DEFAULT nextval('public.car_repair_account_car_repair_account_id_seq'::regclass);


--
-- Name: car_repair_advice_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.car_repair_advice ALTER COLUMN car_repair_advice_id SET DEFAULT nextval('public.car_repair_advice_car_repair_advice_id_seq'::regclass);


--
-- Name: car_repair_price_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.car_repair_price ALTER COLUMN car_repair_price_id SET DEFAULT nextval('public.car_repair_price_car_repair_price_id_seq'::regclass);


--
-- Name: order_part_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.order_part ALTER COLUMN order_part_id SET DEFAULT nextval('public.order_part_order_part_id_seq'::regclass);


--
-- Name: order_work_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.order_work ALTER COLUMN order_work_id SET DEFAULT nextval('public.order_work_order_work_id_seq'::regclass);


--
-- Name: part_store_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.part_store ALTER COLUMN part_store_id SET DEFAULT nextval('public.part_store_part_store_id_seq'::regclass);


--
-- Name: part_type_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.part_type ALTER COLUMN part_type_id SET DEFAULT nextval('public.part_type_part_type_id_seq'::regclass);


--
-- Name: purchase_log_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.purchase_log ALTER COLUMN purchase_log_id SET DEFAULT nextval('public.purchase_log_purchase_log_id_seq'::regclass);


--
-- Name: company_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.repair_company ALTER COLUMN company_id SET DEFAULT nextval('public.repair_company_company_id_seq'::regclass);


--
-- Name: order_id; Type: DEFAULT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.repair_order ALTER COLUMN order_id SET DEFAULT nextval('public.repair_order_order_id_seq'::regclass);



--
-- Name: work_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alekseysamoylov
--

SELECT pg_catalog.setval('public.work_group_id_seq', 1, false);


--
-- Name: work_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alekseysamoylov
--

SELECT pg_catalog.setval('public.work_id_seq', 27, true);


--
-- Name: pk_company_position; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.company_position
    ADD CONSTRAINT pk_company_position PRIMARY KEY (id);


--
-- Name: pk_customer; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT pk_customer PRIMARY KEY (id);


--
-- Name: pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: pk_role; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- Name: pk_test_table; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.test_table
    ADD CONSTRAINT pk_test_table PRIMARY KEY (id);


--
-- Name: pk_users; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);


--
-- Name: pk_work; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.work
    ADD CONSTRAINT pk_work PRIMARY KEY (id);


--
-- Name: pk_work_group; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.work_group
    ADD CONSTRAINT pk_work_group PRIMARY KEY (id);


--
-- Name: user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);


--
-- Name: idx_user_customer_id; Type: INDEX; Schema: public; Owner: alekseysamoylov
--

CREATE INDEX idx_user_customer_id ON public.users USING btree (customer_id);


--
-- Name: idx_work; Type: INDEX; Schema: public; Owner: alekseysamoylov
--

CREATE INDEX idx_work ON public.work USING btree (title, price);


--
-- Name: idx_work_group; Type: INDEX; Schema: public; Owner: alekseysamoylov
--

CREATE INDEX idx_work_group ON public.work_group USING btree (title);


--
-- Name: fk_bonus_for_user; Type: FK CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.bonus
    ADD CONSTRAINT fk_bonus_for_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: fk_company_type_company_position; Type: FK CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.company_position_company_type
    ADD CONSTRAINT fk_company_type_company_position FOREIGN KEY (company_position_id) REFERENCES public.company_position(id);


--
-- Name: fk_message_wrote_by_user; Type: FK CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.work
    ADD CONSTRAINT fk_message_wrote_by_user FOREIGN KEY (work_group_id) REFERENCES public.work_group(id);


--
-- Name: fk_user_role_role; Type: FK CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: fk_user_role_user; Type: FK CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: fk_users_customer_id; Type: FK CONSTRAINT; Schema: public; Owner: alekseysamoylov
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_users_customer_id FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

\connect template1

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

