import { useState } from 'react'
import { useAuth } from '../../context/AuthContext'
import { updateUser } from '../../services/userService'
import Button from '../../atoms/Button'
import FormField from '../../molecules/FormField'
import styles from './ProfilePage.module.css'

/**
 * Page: ProfilePage
 * Perfil del usuario autenticado con edición de nombre.
 */
const ProfilePage = () => {
  const { user, logout } = useAuth()

  const [editing, setEditing] = useState(false)
  const [name, setName] = useState(user?.name ?? '')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')

  const handleSave = async () => {
    if (!name.trim()) {
      setError('El nombre no puede estar vacío')
      return
    }
    setLoading(true)
    setError('')
    setSuccess('')
    try {
      await updateUser(user.id, { name })
      setSuccess('Perfil actualizado correctamente')
      setEditing(false)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al actualizar el perfil')
    } finally {
      setLoading(false)
    }
  }

  const initials = (user?.name ?? user?.email ?? '?')
    .split(' ')
    .map((w) => w[0])
    .join('')
    .toUpperCase()
    .slice(0, 2)

  return (
    <div className={styles.page}>
      <div className={styles.header}>
        <h1 className={styles.title}>Mi perfil</h1>
      </div>

      <div className={styles.layout}>
        {/* Avatar + info */}
        <div className={styles.avatarCard}>
          <div className={styles.avatar}>{initials}</div>
          <p className={styles.avatarName}>{user?.name ?? '—'}</p>
          <p className={styles.avatarEmail}>{user?.email ?? '—'}</p>
          <div className={styles.roles}>
            {user?.roles?.map((role) => (
              <span key={role} className={styles.roleTag}>
                {role.replace('ROLE_', '')}
              </span>
            ))}
          </div>
        </div>

        {/* Datos editables */}
        <div className={styles.detailsCard}>
          <div className={styles.cardHeader}>
            <h2 className={styles.cardTitle}>Información personal</h2>
            {!editing && (
              <button className={styles.editBtn} onClick={() => setEditing(true)}>
                ✏️ Editar
              </button>
            )}
          </div>

          {success && <p className={styles.successMsg}>✓ {success}</p>}
          {error && <p className={styles.errorMsg}>⚠️ {error}</p>}

          <div className={styles.fields}>
            <FormField
              label="Nombre"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              disabled={!editing}
              placeholder="Tu nombre"
            />
            <FormField
              label="Email"
              id="email"
              value={user?.email ?? ''}
              disabled
              placeholder="tu@email.com"
            />
          </div>

          {editing && (
            <div className={styles.editActions}>
              <Button variant="primary" onClick={handleSave} loading={loading}>
                Guardar cambios
              </Button>
              <Button
                variant="ghost"
                onClick={() => {
                  setEditing(false)
                  setName(user?.name ?? '')
                  setError('')
                }}
              >
                Cancelar
              </Button>
            </div>
          )}

          <div className={styles.dangerZone}>
            <h3 className={styles.dangerTitle}>Sesión</h3>
            <Button variant="danger" onClick={logout}>
              Cerrar sesión
            </Button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProfilePage
