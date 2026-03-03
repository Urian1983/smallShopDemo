import { forwardRef } from 'react'
import Input from '../../atoms/Input'
import styles from './FormField.module.css'

/**
 * Molecule: FormField
 * Combina label + Input + mensaje de error en un bloque coherente
 */
const FormField = forwardRef(({
  id,
  label,
  type = 'text',
  placeholder,
  error,
  required = false,
  disabled = false,
  className = '',
  ...rest
}, ref) => {
  return (
    <div className={[styles.field, className].filter(Boolean).join(' ')}>
      {label && (
        <label htmlFor={id} className={styles.label}>
          {label}
          {required && <span className={styles.required} aria-hidden="true"> *</span>}
        </label>
      )}
      <Input
        ref={ref}
        id={id}
        type={type}
        placeholder={placeholder}
        error={error}
        disabled={disabled}
        {...rest}
      />
    </div>
  )
})

FormField.displayName = 'FormField'

export default FormField
