const jsonServer = require('json-server');
const jwt = require('jsonwebtoken');

const server = jsonServer.create();
const router = jsonServer.router('db.json');
const middlewares = jsonServer.defaults();
const SECRET = 'edu_secret_jwt_2024';

server.use(middlewares);
server.use(jsonServer.bodyParser);

// Endpoint de login: genera un JWT real
server.post('/auth/login', (req, res) => {
  const { email, password } = req.body;
  const db = router.db;
  const user = db.get('users').find({ email, password }).value();

  if (!user) {
    return res.status(401).json({ message: 'Credenciales inválidas' });
  }

  const { password: _pass, ...userWithoutPass } = user;
  const token = jwt.sign(
    { id: user.id, email: user.email, rol: user.rol },
    SECRET,
    { expiresIn: '1h' }
  );

  res.json({ token, user: userWithoutPass });
});

// Endpoint de registro: crea un nuevo usuario
server.post('/auth/register', (req, res) => {
  const { nombre, email, password, rol } = req.body;
  const db = router.db;

  if (!nombre || !email || !password || !rol) {
    return res.status(400).json({ message: 'Todos los campos son requeridos' });
  }

  const existe = db.get('users').find({ email }).value();
  if (existe) {
    return res.status(409).json({ message: 'El correo ya está registrado' });
  }

  const users = db.get('users').value();
  const newId = users.length > 0 ? Math.max(...users.map(u => u.id)) + 1 : 1;
  const newUser = { id: newId, nombre, email, password, rol, activo: true };

  db.get('users').push(newUser).write();
  const { password: _pass, ...userWithoutPass } = newUser;
  res.status(201).json(userWithoutPass);
});

// Middleware de protección para rutas de escritura
server.use((req, res, next) => {
  if (req.method === 'GET' || req.path === '/auth/login') {
    return next();
  }

  const authHeader = req.headers['authorization'];
  if (!authHeader || !authHeader.startsWith('Bearer ')) {
    return res.status(401).json({ message: 'Token requerido' });
  }

  try {
    jwt.verify(authHeader.split(' ')[1], SECRET);
    next();
  } catch {
    res.status(401).json({ message: 'Token inválido o expirado' });
  }
});

server.use(router);
server.listen(3000, () => {
  console.log('🚀 API con JWT corriendo en http://localhost:3000');
  console.log('📋 Usuarios: admin@edu.com/admin123 | profesor@edu.com/prof123 | estudiante@edu.com/est123');
});
