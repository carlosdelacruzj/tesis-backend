-- Tabla cliente (nombres en español para respetar el backend original)
-- Usamos UUID como PK en Postgres (equivalente al id autoincrement de MySQL).
create extension if not exists pgcrypto;

create table if not exists cliente (
  id_cliente uuid primary key default gen_random_uuid(),
  nombre     varchar(100) not null,
  apellido   varchar(100) not null,
  correo     varchar(254),
  num_doc    varchar(32),
  celular    varchar(32),
  direccion  text,
  creado_en  timestamptz not null default now()
);

-- Opcionales (quítalos si no quieres restricciones):
create index if not exists idx_cliente_correo on cliente(correo);
create index if not exists idx_cliente_numdoc on cliente(num_doc);
