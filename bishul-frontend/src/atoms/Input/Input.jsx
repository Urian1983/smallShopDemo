import { forwardRef } from 'react'
import styles from './Input.module.css'

/**
 * Atom: Input
 * @param {string} id
 * @param {string} type
 * @param {string} placeholder
 * @param {string} error  — mensaje de error
 * @param {boolean} disabled
 */
const Input = forwardRef(({
  id,
  type = 'text',
  placeholder,
  error,
  disabled = false,
  className = '',
  ...rest
}, ref) => {
  const classes = [
    styles.input,
    error ? styles.hasError : '',
    className,
  ]
    .filter(Boolean)
    .join(' ')

  return (
    <div className={styles.wrapper}>
      <input
        ref={ref}
        id={id}
        type={type}
        placeholder={placeholder}
        disabled={disabled}
        className={classes}
        aria-invalid={!!error}
        {...rest}
      />
      {error && (
        <span className={styles.errorMsg} role="alert">
          {error}
        </span>
      )}
    </div>
  )
})

Input.displayName = 'Input'

export default Input
