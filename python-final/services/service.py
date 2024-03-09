from abc import ABC, abstractmethod

class Service(ABC):
    @abstractmethod
    def create() -> None:
        pass
    
    @abstractmethod
    def index(username: str = None) -> list:
        pass