from typing import Optional
from uuid import UUID

from sqlalchemy import String, Text
from sqlalchemy.dialects.postgresql import UUID as PUUID
from sqlalchemy.orm import Mapped, mapped_column

from .base import Base


class Artist(Base):
    __tablename__ = "artists"

    id: Mapped[UUID] = mapped_column(PUUID, primary_key=True)

    name: Mapped[str] = mapped_column(String(64), nullable=False)

    biography: Mapped[Optional[str]] = mapped_column(Text, nullable=True)
