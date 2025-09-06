-- V2__create_pedido.sql  (PostgreSQL)
-- Si quieres que la DB genere UUID cuando no lo envía la app, descomenta la extensión
-- CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS pedido (
  id_pedido UUID PRIMARY KEY,                      -- si usas DEFAULT, pon: DEFAULT gen_random_uuid()
  nombre     VARCHAR(100)    NOT NULL,
  creado_en  TIMESTAMPTZ     NOT NULL DEFAULT now(),
  servicio   VARCHAR(100)    NOT NULL,
  evento     VARCHAR(100)    NOT NULL,
  cliente    VARCHAR(100)    NOT NULL
);

-- Índice útil para tus listados por fecha (creadoEn,desc)
CREATE INDEX IF NOT EXISTS idx_pedido_creado_en ON pedido (creado_en DESC);
