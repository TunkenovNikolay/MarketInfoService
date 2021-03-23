--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-03-23 11:44:18

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 16409)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id integer NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16407)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO postgres;

--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 200
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- TOC entry 203 (class 1259 OID 16420)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(30,2) NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16418)
-- Name: goods_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.goods_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.goods_id_seq OWNER TO postgres;

--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 202
-- Name: goods_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.goods_id_seq OWNED BY public.products.id;


--
-- TOC entry 205 (class 1259 OID 16428)
-- Name: purchases; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchases (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    product_id integer NOT NULL,
    purchase_date date NOT NULL
);


ALTER TABLE public.purchases OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16426)
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.purchases_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchases_id_seq OWNER TO postgres;

--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 204
-- Name: purchases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;


--
-- TOC entry 2863 (class 2604 OID 16412)
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- TOC entry 2864 (class 2604 OID 16423)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.goods_id_seq'::regclass);


--
-- TOC entry 2865 (class 2604 OID 16431)
-- Name: purchases id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);


--
-- TOC entry 3005 (class 0 OID 16409)
-- Dependencies: 201
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers VALUES (1, 'Иван', 'Петров');
INSERT INTO public.customers VALUES (2, 'Николай', 'Янович');
INSERT INTO public.customers VALUES (3, 'Сергей', 'Полянский');
INSERT INTO public.customers VALUES (4, 'Евгений', 'Елисов');
INSERT INTO public.customers VALUES (5, 'Полина', 'Тункенова');
INSERT INTO public.customers VALUES (6, 'Наталья', 'Гребенюк');


--
-- TOC entry 3007 (class 0 OID 16420)
-- Dependencies: 203
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products VALUES (1, 'Минеральная вода', 50.00);
INSERT INTO public.products VALUES (2, 'Хлеб', 25.00);
INSERT INTO public.products VALUES (3, 'Огурец', 150.00);
INSERT INTO public.products VALUES (4, 'Помидор', 100.00);
INSERT INTO public.products VALUES (5, 'Сахар', 50.00);
INSERT INTO public.products VALUES (6, 'Сок', 100.00);
INSERT INTO public.products VALUES (7, 'Изюм', 60.00);
INSERT INTO public.products VALUES (8, 'Картофель', 45.00);


--
-- TOC entry 3009 (class 0 OID 16428)
-- Dependencies: 205
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchases VALUES (1, 1, 1, '2020-01-14');
INSERT INTO public.purchases VALUES (2, 1, 2, '2020-01-14');
INSERT INTO public.purchases VALUES (3, 1, 1, '2020-01-14');
INSERT INTO public.purchases VALUES (4, 1, 3, '2020-01-14');
INSERT INTO public.purchases VALUES (5, 1, 2, '2020-02-01');
INSERT INTO public.purchases VALUES (6, 2, 4, '2020-01-16');
INSERT INTO public.purchases VALUES (7, 2, 8, '2020-01-16');
INSERT INTO public.purchases VALUES (8, 2, 6, '2020-01-16');
INSERT INTO public.purchases VALUES (9, 2, 2, '2020-01-16');
INSERT INTO public.purchases VALUES (10, 3, 1, '2020-01-03');
INSERT INTO public.purchases VALUES (11, 4, 1, '2020-02-25');
INSERT INTO public.purchases VALUES (12, 4, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (13, 4, 5, '2020-02-25');
INSERT INTO public.purchases VALUES (14, 5, 2, '2020-03-25');
INSERT INTO public.purchases VALUES (15, 5, 5, '2020-03-25');
INSERT INTO public.purchases VALUES (16, 5, 1, '2020-01-14');
INSERT INTO public.purchases VALUES (17, 5, 3, '2020-01-14');
INSERT INTO public.purchases VALUES (18, 6, 1, '2020-03-12');
INSERT INTO public.purchases VALUES (19, 6, 2, '2020-03-12');


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 200
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 6, true);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 202
-- Name: goods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.goods_id_seq', 8, true);


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 204
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchases_id_seq', 19, true);


--
-- TOC entry 2867 (class 2606 OID 16417)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 2869 (class 2606 OID 16425)
-- Name: products goods_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT goods_pkey PRIMARY KEY (id);


--
-- TOC entry 2871 (class 2606 OID 16433)
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);


--
-- TOC entry 2872 (class 2606 OID 16434)
-- Name: purchases purchases_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 2873 (class 2606 OID 16439)
-- Name: purchases purchases_good_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_good_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);


-- Completed on 2021-03-23 11:44:18

--
-- PostgreSQL database dump complete
--

