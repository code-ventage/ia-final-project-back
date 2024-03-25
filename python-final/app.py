import json

from services.service import Service
from services.userScoreService import UserScoreService
from services.userService import UserService

# Intenta ejecutar el siguiente bloque de código
try:
    # Abre el archivo 'request.json' en modo lectura
    with open('request.json', 'r') as file:
        # Carga el contenido del archivo en la variable 'request'
        request = json.load(file)
        # Asigna el valor de 'version' en 'request' a 'version' en 'response' de 'Service'
        Service.response['version'] = request['version']

        # Obtiene el servicio y el método de 'request' y los ejecuta con 'data' como argumento
        getattr(globals()[request['service']], request['method'])(request['data'])

# Si ocurre una excepción durante la ejecución del bloque de código anterior
except Exception as e:
    # Asigna el estado de 'response' en 'Service' a 500
    Service.response['response']['status'] = 500
    # Asigna el mensaje de 'response' en 'Service' al mensaje de la excepción
    Service.response['response']['message'] = str(e)
    # Abre el archivo 'response.json' en modo escritura
    with open('response.json', 'w') as file:
        # Escribe la respuesta en el archivo 'response.json'
        json.dump(Service.response, file, indent=4)
# Finalmente, independientemente de si ocurrió una excepción o no
finally:
    # Restablece la respuesta a su valor predeterminado
    Service.resetResponse()