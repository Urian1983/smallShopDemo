import Skeleton from '../../atoms/Skeleton'
import styles from './ProductCardSkeleton.module.css'

/**
 * Molecule: ProductCardSkeleton
 * Placeholder animado con la misma forma que ProductCard.
 */
const ProductCardSkeleton = () => (
  <div className={styles.card}>
    <Skeleton variant="rect" height="200px" className={styles.image} />
    <div className={styles.body}>
      <Skeleton variant="text" width="60%" />
      <Skeleton variant="text" width="85%" />
      <Skeleton variant="text" width="40%" />
      <div className={styles.footer}>
        <Skeleton variant="rect" width="70px" height="28px" />
        <Skeleton variant="rect" width="90px" height="36px" />
      </div>
    </div>
  </div>
)

export default ProductCardSkeleton
